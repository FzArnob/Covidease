package com.firebase.client.core.view;

import com.firebase.client.core.CompoundWrite;
import com.firebase.client.core.Path;
import com.firebase.client.core.WriteTreeRef;
import com.firebase.client.core.operation.AckUserWrite;
import com.firebase.client.core.operation.Merge;
import com.firebase.client.core.operation.Operation;
import com.firebase.client.core.operation.Overwrite;
import com.firebase.client.core.utilities.ImmutableTree;
import com.firebase.client.core.view.filter.ChildChangeAccumulator;
import com.firebase.client.core.view.filter.NodeFilter;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.ChildrenNode;
import com.firebase.client.snapshot.EmptyNode;
import com.firebase.client.snapshot.Index;
import com.firebase.client.snapshot.IndexedNode;
import com.firebase.client.snapshot.KeyIndex;
import com.firebase.client.snapshot.NamedNode;
import com.firebase.client.snapshot.Node;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ViewProcessor {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static NodeFilter.CompleteChildSource NO_COMPLETE_SOURCE;
    private final NodeFilter filter;

    static {
        boolean z;
        NodeFilter.CompleteChildSource completeChildSource;
        if (!ViewProcessor.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
        new NodeFilter.CompleteChildSource() {
            public Node getCompleteChild(ChildKey childKey) {
                ChildKey childKey2 = childKey;
                return null;
            }

            public NamedNode getChildAfterChild(Index index, NamedNode namedNode, boolean z) {
                Index index2 = index;
                NamedNode namedNode2 = namedNode;
                boolean z2 = z;
                return null;
            }
        };
        NO_COMPLETE_SOURCE = completeChildSource;
    }

    public ViewProcessor(NodeFilter filter2) {
        this.filter = filter2;
    }

    public static class ProcessorResult {
        public final List<Change> changes;
        public final ViewCache viewCache;

        public ProcessorResult(ViewCache viewCache2, List<Change> changes2) {
            this.viewCache = viewCache2;
            this.changes = changes2;
        }
    }

    public ProcessorResult applyOperation(ViewCache viewCache, Operation operation, WriteTreeRef writeTreeRef, Node node) {
        ChildChangeAccumulator childChangeAccumulator;
        ViewCache newViewCache;
        Throwable th;
        List<Change> list;
        ProcessorResult processorResult;
        Throwable th2;
        Throwable th3;
        StringBuilder sb;
        ViewCache oldViewCache = viewCache;
        Operation operation2 = operation;
        WriteTreeRef writesCache = writeTreeRef;
        Node optCompleteCache = node;
        new ChildChangeAccumulator();
        ChildChangeAccumulator accumulator = childChangeAccumulator;
        switch (operation2.getType()) {
            case Overwrite:
                Overwrite overwrite = (Overwrite) operation2;
                if (overwrite.getSource().isFromUser()) {
                    newViewCache = applyUserOverwrite(oldViewCache, overwrite.getPath(), overwrite.getSnapshot(), writesCache, optCompleteCache, accumulator);
                    break;
                } else if ($assertionsDisabled || overwrite.getSource().isFromServer()) {
                    newViewCache = applyServerOverwrite(oldViewCache, overwrite.getPath(), overwrite.getSnapshot(), writesCache, optCompleteCache, overwrite.getSource().isTagged() || (oldViewCache.getServerCache().isFiltered() && !overwrite.getPath().isEmpty()), accumulator);
                    break;
                } else {
                    Throwable th4 = th2;
                    new AssertionError();
                    throw th4;
                }
            case Merge:
                Merge merge = (Merge) operation2;
                if (merge.getSource().isFromUser()) {
                    newViewCache = applyUserMerge(oldViewCache, merge.getPath(), merge.getChildren(), writesCache, optCompleteCache, accumulator);
                    break;
                } else if ($assertionsDisabled || merge.getSource().isFromServer()) {
                    newViewCache = applyServerMerge(oldViewCache, merge.getPath(), merge.getChildren(), writesCache, optCompleteCache, merge.getSource().isTagged() || oldViewCache.getServerCache().isFiltered(), accumulator);
                    break;
                } else {
                    Throwable th5 = th;
                    new AssertionError();
                    throw th5;
                }
            case AckUserWrite:
                AckUserWrite ackUserWrite = (AckUserWrite) operation2;
                if (ackUserWrite.isRevert()) {
                    newViewCache = revertUserWrite(oldViewCache, ackUserWrite.getPath(), writesCache, optCompleteCache, accumulator);
                    break;
                } else {
                    newViewCache = ackUserWrite(oldViewCache, ackUserWrite.getPath(), ackUserWrite.getAffectedTree(), writesCache, optCompleteCache, accumulator);
                    break;
                }
            case ListenComplete:
                newViewCache = listenComplete(oldViewCache, operation2.getPath(), writesCache, optCompleteCache, accumulator);
                break;
            default:
                Throwable th6 = th3;
                new StringBuilder();
                new AssertionError(sb.append("Unknown operation: ").append(operation2.getType()).toString());
                throw th6;
        }
        new ArrayList<>(accumulator.getChanges());
        List<Change> changes = list;
        maybeAddValueEvent(oldViewCache, newViewCache, changes);
        new ProcessorResult(newViewCache, changes);
        return processorResult;
    }

    private void maybeAddValueEvent(ViewCache viewCache, ViewCache newViewCache, List<Change> list) {
        ViewCache oldViewCache = viewCache;
        List<Change> accumulator = list;
        CacheNode eventSnap = newViewCache.getEventCache();
        if (eventSnap.isFullyInitialized()) {
            boolean isLeafOrEmpty = eventSnap.getNode().isLeafNode() || eventSnap.getNode().isEmpty();
            if (!accumulator.isEmpty() || !oldViewCache.getEventCache().isFullyInitialized() || ((isLeafOrEmpty && !eventSnap.getNode().equals(oldViewCache.getCompleteEventSnap())) || !eventSnap.getNode().getPriority().equals(oldViewCache.getCompleteEventSnap().getPriority()))) {
                boolean add = accumulator.add(Change.valueChange(eventSnap.getIndexedNode()));
            }
        }
    }

    private ViewCache generateEventCacheAfterServerEvent(ViewCache viewCache, Path path, WriteTreeRef writeTreeRef, NodeFilter.CompleteChildSource completeChildSource, ChildChangeAccumulator childChangeAccumulator) {
        Node newEventChild;
        IndexedNode newEventCache;
        Throwable th;
        boolean z;
        Node nodeWithLocalWrites;
        Throwable th2;
        ViewCache viewCache2 = viewCache;
        Path changePath = path;
        WriteTreeRef writesCache = writeTreeRef;
        NodeFilter.CompleteChildSource source = completeChildSource;
        ChildChangeAccumulator accumulator = childChangeAccumulator;
        CacheNode oldEventSnap = viewCache2.getEventCache();
        if (writesCache.shadowingWrite(changePath) != null) {
            return viewCache2;
        }
        if (!changePath.isEmpty()) {
            ChildKey childKey = changePath.getFront();
            if (!childKey.isPriorityChildName()) {
                Path childChangePath = changePath.popFront();
                if (oldEventSnap.isCompleteForChild(childKey)) {
                    Node eventChildUpdate = writesCache.calcEventCacheAfterServerOverwrite(changePath, oldEventSnap.getNode(), viewCache2.getServerCache().getNode());
                    if (eventChildUpdate != null) {
                        newEventChild = oldEventSnap.getNode().getImmediateChild(childKey).updateChild(childChangePath, eventChildUpdate);
                    } else {
                        newEventChild = oldEventSnap.getNode().getImmediateChild(childKey);
                    }
                } else {
                    newEventChild = writesCache.calcCompleteChild(childKey, viewCache2.getServerCache());
                }
                if (newEventChild != null) {
                    newEventCache = this.filter.updateChild(oldEventSnap.getIndexedNode(), childKey, newEventChild, childChangePath, source, accumulator);
                } else {
                    newEventCache = oldEventSnap.getIndexedNode();
                }
            } else if ($assertionsDisabled || changePath.size() == 1) {
                Node updatedPriority = writesCache.calcEventCacheAfterServerOverwrite(changePath, oldEventSnap.getNode(), viewCache2.getServerCache().getNode());
                if (updatedPriority != null) {
                    newEventCache = this.filter.updatePriority(oldEventSnap.getIndexedNode(), updatedPriority);
                } else {
                    newEventCache = oldEventSnap.getIndexedNode();
                }
            } else {
                Throwable th3 = th;
                new AssertionError("Can't have a priority with additional path components");
                throw th3;
            }
        } else if ($assertionsDisabled || viewCache2.getServerCache().isFullyInitialized()) {
            if (viewCache2.getServerCache().isFiltered()) {
                Node serverCache = viewCache2.getCompleteServerSnap();
                nodeWithLocalWrites = writesCache.calcCompleteEventChildren(serverCache instanceof ChildrenNode ? serverCache : EmptyNode.Empty());
            } else {
                nodeWithLocalWrites = writesCache.calcCompleteEventCache(viewCache2.getCompleteServerSnap());
            }
            newEventCache = this.filter.updateFullNode(viewCache2.getEventCache().getIndexedNode(), IndexedNode.from(nodeWithLocalWrites, this.filter.getIndex()), accumulator);
        } else {
            Throwable th4 = th2;
            new AssertionError("If change path is empty, we must have complete server data");
            throw th4;
        }
        ViewCache viewCache3 = viewCache2;
        IndexedNode indexedNode = newEventCache;
        if (oldEventSnap.isFullyInitialized() || changePath.isEmpty()) {
            z = true;
        } else {
            z = false;
        }
        return viewCache3.updateEventSnap(indexedNode, z, this.filter.filtersNodes());
    }

    private ViewCache applyServerOverwrite(ViewCache viewCache, Path path, Node node, WriteTreeRef writeTreeRef, Node node2, boolean filterServerNode, ChildChangeAccumulator childChangeAccumulator) {
        IndexedNode newServerCache;
        Throwable th;
        NodeFilter.CompleteChildSource source;
        ViewCache oldViewCache = viewCache;
        Path changePath = path;
        Node changedSnap = node;
        WriteTreeRef writesCache = writeTreeRef;
        Node optCompleteCache = node2;
        ChildChangeAccumulator accumulator = childChangeAccumulator;
        CacheNode oldServerSnap = oldViewCache.getServerCache();
        NodeFilter serverFilter = filterServerNode ? this.filter : this.filter.getIndexedFilter();
        if (changePath.isEmpty()) {
            newServerCache = serverFilter.updateFullNode(oldServerSnap.getIndexedNode(), IndexedNode.from(changedSnap, serverFilter.getIndex()), (ChildChangeAccumulator) null);
        } else if (!serverFilter.filtersNodes() || oldServerSnap.isFiltered()) {
            ChildKey childKey = changePath.getFront();
            if (!oldServerSnap.isCompleteForPath(changePath) && changePath.size() > 1) {
                return oldViewCache;
            }
            Path childChangePath = changePath.popFront();
            Node newChildNode = oldServerSnap.getNode().getImmediateChild(childKey).updateChild(childChangePath, changedSnap);
            if (childKey.isPriorityChildName()) {
                newServerCache = serverFilter.updatePriority(oldServerSnap.getIndexedNode(), newChildNode);
            } else {
                newServerCache = serverFilter.updateChild(oldServerSnap.getIndexedNode(), childKey, newChildNode, childChangePath, NO_COMPLETE_SOURCE, (ChildChangeAccumulator) null);
            }
        } else if ($assertionsDisabled || !changePath.isEmpty()) {
            ChildKey childKey2 = changePath.getFront();
            newServerCache = serverFilter.updateFullNode(oldServerSnap.getIndexedNode(), oldServerSnap.getIndexedNode().updateChild(childKey2, oldServerSnap.getNode().getImmediateChild(childKey2).updateChild(changePath.popFront(), changedSnap)), (ChildChangeAccumulator) null);
        } else {
            Throwable th2 = th;
            new AssertionError("An empty path should have been caught in the other branch");
            throw th2;
        }
        ViewCache newViewCache = oldViewCache.updateServerSnap(newServerCache, oldServerSnap.isFullyInitialized() || changePath.isEmpty(), serverFilter.filtersNodes());
        new WriteTreeCompleteChildSource(writesCache, newViewCache, optCompleteCache);
        return generateEventCacheAfterServerEvent(newViewCache, changePath, writesCache, source, accumulator);
    }

    private ViewCache applyUserOverwrite(ViewCache viewCache, Path path, Node node, WriteTreeRef writesCache, Node optCompleteCache, ChildChangeAccumulator childChangeAccumulator) {
        NodeFilter.CompleteChildSource completeChildSource;
        Node newChild;
        ViewCache newViewCache;
        ViewCache oldViewCache = viewCache;
        Path changePath = path;
        Node changedSnap = node;
        ChildChangeAccumulator accumulator = childChangeAccumulator;
        CacheNode oldEventSnap = oldViewCache.getEventCache();
        new WriteTreeCompleteChildSource(writesCache, oldViewCache, optCompleteCache);
        NodeFilter.CompleteChildSource source = completeChildSource;
        if (changePath.isEmpty()) {
            newViewCache = oldViewCache.updateEventSnap(this.filter.updateFullNode(oldViewCache.getEventCache().getIndexedNode(), IndexedNode.from(changedSnap, this.filter.getIndex()), accumulator), true, this.filter.filtersNodes());
        } else {
            ChildKey childKey = changePath.getFront();
            if (childKey.isPriorityChildName()) {
                newViewCache = oldViewCache.updateEventSnap(this.filter.updatePriority(oldViewCache.getEventCache().getIndexedNode(), changedSnap), oldEventSnap.isFullyInitialized(), oldEventSnap.isFiltered());
            } else {
                Path childChangePath = changePath.popFront();
                Node oldChild = oldEventSnap.getNode().getImmediateChild(childKey);
                if (childChangePath.isEmpty()) {
                    newChild = changedSnap;
                } else {
                    Node childNode = source.getCompleteChild(childKey);
                    if (childNode == null) {
                        newChild = EmptyNode.Empty();
                    } else if (!childChangePath.getBack().isPriorityChildName() || !childNode.getChild(childChangePath.getParent()).isEmpty()) {
                        newChild = childNode.updateChild(childChangePath, changedSnap);
                    } else {
                        newChild = childNode;
                    }
                }
                if (!oldChild.equals(newChild)) {
                    newViewCache = oldViewCache.updateEventSnap(this.filter.updateChild(oldEventSnap.getIndexedNode(), childKey, newChild, childChangePath, source, accumulator), oldEventSnap.isFullyInitialized(), this.filter.filtersNodes());
                } else {
                    newViewCache = oldViewCache;
                }
            }
        }
        return newViewCache;
    }

    private static boolean cacheHasChild(ViewCache viewCache, ChildKey childKey) {
        return viewCache.getEventCache().isCompleteForChild(childKey);
    }

    private ViewCache applyUserMerge(ViewCache viewCache, Path path, CompoundWrite compoundWrite, WriteTreeRef writeTreeRef, Node node, ChildChangeAccumulator childChangeAccumulator) {
        Throwable th;
        ViewCache viewCache2 = viewCache;
        Path path2 = path;
        CompoundWrite changedChildren = compoundWrite;
        WriteTreeRef writesCache = writeTreeRef;
        Node serverCache = node;
        ChildChangeAccumulator accumulator = childChangeAccumulator;
        if ($assertionsDisabled || changedChildren.rootWrite() == null) {
            ViewCache currentViewCache = viewCache2;
            Iterator i$ = changedChildren.iterator();
            while (i$.hasNext()) {
                Map.Entry<Path, Node> entry = i$.next();
                Path writePath = path2.child(entry.getKey());
                if (cacheHasChild(viewCache2, writePath.getFront())) {
                    currentViewCache = applyUserOverwrite(currentViewCache, writePath, entry.getValue(), writesCache, serverCache, accumulator);
                }
            }
            Iterator i$2 = changedChildren.iterator();
            while (i$2.hasNext()) {
                Map.Entry<Path, Node> entry2 = i$2.next();
                Path writePath2 = path2.child(entry2.getKey());
                if (!cacheHasChild(viewCache2, writePath2.getFront())) {
                    currentViewCache = applyUserOverwrite(currentViewCache, writePath2, entry2.getValue(), writesCache, serverCache, accumulator);
                }
            }
            return currentViewCache;
        }
        Throwable th2 = th;
        new AssertionError("Can't have a merge that is an overwrite");
        throw th2;
    }

    private ViewCache applyServerMerge(ViewCache viewCache, Path path, CompoundWrite compoundWrite, WriteTreeRef writeTreeRef, Node node, boolean z, ChildChangeAccumulator childChangeAccumulator) {
        CompoundWrite actualMerge;
        Path path2;
        Path path3;
        Throwable th;
        ViewCache viewCache2 = viewCache;
        Path path4 = path;
        CompoundWrite changedChildren = compoundWrite;
        WriteTreeRef writesCache = writeTreeRef;
        Node serverCache = node;
        boolean filterServerNode = z;
        ChildChangeAccumulator accumulator = childChangeAccumulator;
        if (viewCache2.getServerCache().getNode().isEmpty() && !viewCache2.getServerCache().isFullyInitialized()) {
            return viewCache2;
        }
        ViewCache curViewCache = viewCache2;
        if ($assertionsDisabled || changedChildren.rootWrite() == null) {
            if (path4.isEmpty()) {
                actualMerge = changedChildren;
            } else {
                actualMerge = CompoundWrite.emptyWrite().addWrites(path4, changedChildren);
            }
            Node serverNode = viewCache2.getServerCache().getNode();
            Map<ChildKey, CompoundWrite> childCompoundWrites = actualMerge.childCompoundWrites();
            for (Map.Entry<ChildKey, CompoundWrite> childMerge : childCompoundWrites.entrySet()) {
                ChildKey childKey = childMerge.getKey();
                if (serverNode.hasChild(childKey)) {
                    Node newChild = childMerge.getValue().apply(serverNode.getImmediateChild(childKey));
                    new Path(childKey);
                    curViewCache = applyServerOverwrite(curViewCache, path3, newChild, writesCache, serverCache, filterServerNode, accumulator);
                }
            }
            for (Map.Entry<ChildKey, CompoundWrite> childMerge2 : childCompoundWrites.entrySet()) {
                ChildKey childKey2 = childMerge2.getKey();
                boolean isUnknownDeepMerge = !viewCache2.getServerCache().isCompleteForChild(childKey2) && childMerge2.getValue().rootWrite() == null;
                if (!serverNode.hasChild(childKey2) && !isUnknownDeepMerge) {
                    Node newChild2 = childMerge2.getValue().apply(serverNode.getImmediateChild(childKey2));
                    new Path(childKey2);
                    curViewCache = applyServerOverwrite(curViewCache, path2, newChild2, writesCache, serverCache, filterServerNode, accumulator);
                }
            }
            return curViewCache;
        }
        Throwable th2 = th;
        new AssertionError("Can't have a merge that is an overwrite");
        throw th2;
    }

    private ViewCache ackUserWrite(ViewCache viewCache, Path path, ImmutableTree<Boolean> immutableTree, WriteTreeRef writeTreeRef, Node node, ChildChangeAccumulator childChangeAccumulator) {
        ViewCache viewCache2 = viewCache;
        Path ackPath = path;
        ImmutableTree<Boolean> affectedTree = immutableTree;
        WriteTreeRef writesCache = writeTreeRef;
        Node optCompleteCache = node;
        ChildChangeAccumulator accumulator = childChangeAccumulator;
        if (writesCache.shadowingWrite(ackPath) != null) {
            return viewCache2;
        }
        boolean filterServerNode = viewCache2.getServerCache().isFiltered();
        CacheNode serverCache = viewCache2.getServerCache();
        if (affectedTree.getValue() == null) {
            CompoundWrite changedChildren = CompoundWrite.emptyWrite();
            Iterator i$ = affectedTree.iterator();
            while (i$.hasNext()) {
                Path mergePath = (Path) i$.next().getKey();
                Path serverCachePath = ackPath.child(mergePath);
                if (serverCache.isCompleteForPath(serverCachePath)) {
                    changedChildren = changedChildren.addWrite(mergePath, serverCache.getNode().getChild(serverCachePath));
                }
            }
            return applyServerMerge(viewCache2, ackPath, changedChildren, writesCache, optCompleteCache, filterServerNode, accumulator);
        } else if ((ackPath.isEmpty() && serverCache.isFullyInitialized()) || serverCache.isCompleteForPath(ackPath)) {
            return applyServerOverwrite(viewCache2, ackPath, serverCache.getNode().getChild(ackPath), writesCache, optCompleteCache, filterServerNode, accumulator);
        } else {
            if (!ackPath.isEmpty()) {
                return viewCache2;
            }
            CompoundWrite changedChildren2 = CompoundWrite.emptyWrite();
            for (NamedNode child : serverCache.getNode()) {
                changedChildren2 = changedChildren2.addWrite(child.getName(), child.getNode());
            }
            return applyServerMerge(viewCache2, ackPath, changedChildren2, writesCache, optCompleteCache, filterServerNode, accumulator);
        }
    }

    public ViewCache revertUserWrite(ViewCache viewCache, Path path, WriteTreeRef writeTreeRef, Node node, ChildChangeAccumulator childChangeAccumulator) {
        NodeFilter.CompleteChildSource completeChildSource;
        IndexedNode newEventCache;
        Node newNode;
        ViewCache viewCache2 = viewCache;
        Path path2 = path;
        WriteTreeRef writesCache = writeTreeRef;
        Node optCompleteServerCache = node;
        ChildChangeAccumulator accumulator = childChangeAccumulator;
        if (writesCache.shadowingWrite(path2) != null) {
            return viewCache2;
        }
        new WriteTreeCompleteChildSource(writesCache, viewCache2, optCompleteServerCache);
        NodeFilter.CompleteChildSource source = completeChildSource;
        IndexedNode oldEventCache = viewCache2.getEventCache().getIndexedNode();
        if (path2.isEmpty() || path2.getFront().isPriorityChildName()) {
            if (viewCache2.getServerCache().isFullyInitialized()) {
                newNode = writesCache.calcCompleteEventCache(viewCache2.getCompleteServerSnap());
            } else {
                newNode = writesCache.calcCompleteEventChildren(viewCache2.getServerCache().getNode());
            }
            newEventCache = this.filter.updateFullNode(oldEventCache, IndexedNode.from(newNode, this.filter.getIndex()), accumulator);
        } else {
            ChildKey childKey = path2.getFront();
            Node newChild = writesCache.calcCompleteChild(childKey, viewCache2.getServerCache());
            if (newChild == null && viewCache2.getServerCache().isCompleteForChild(childKey)) {
                newChild = oldEventCache.getNode().getImmediateChild(childKey);
            }
            if (newChild != null) {
                newEventCache = this.filter.updateChild(oldEventCache, childKey, newChild, path2.popFront(), source, accumulator);
            } else if (newChild != null || !viewCache2.getEventCache().getNode().hasChild(childKey)) {
                newEventCache = oldEventCache;
            } else {
                newEventCache = this.filter.updateChild(oldEventCache, childKey, EmptyNode.Empty(), path2.popFront(), source, accumulator);
            }
            if (newEventCache.getNode().isEmpty() && viewCache2.getServerCache().isFullyInitialized()) {
                Node complete = writesCache.calcCompleteEventCache(viewCache2.getCompleteServerSnap());
                if (complete.isLeafNode()) {
                    newEventCache = this.filter.updateFullNode(newEventCache, IndexedNode.from(complete, this.filter.getIndex()), accumulator);
                }
            }
        }
        return viewCache2.updateEventSnap(newEventCache, viewCache2.getServerCache().isFullyInitialized() || writesCache.shadowingWrite(Path.getEmptyPath()) != null, this.filter.filtersNodes());
    }

    private ViewCache listenComplete(ViewCache viewCache, Path path, WriteTreeRef writeTreeRef, Node node, ChildChangeAccumulator childChangeAccumulator) {
        ViewCache viewCache2 = viewCache;
        Path path2 = path;
        WriteTreeRef writesCache = writeTreeRef;
        Node node2 = node;
        ChildChangeAccumulator accumulator = childChangeAccumulator;
        CacheNode oldServerNode = viewCache2.getServerCache();
        return generateEventCacheAfterServerEvent(viewCache2.updateServerSnap(oldServerNode.getIndexedNode(), oldServerNode.isFullyInitialized() || path2.isEmpty(), oldServerNode.isFiltered()), path2, writesCache, NO_COMPLETE_SOURCE, accumulator);
    }

    private static class WriteTreeCompleteChildSource implements NodeFilter.CompleteChildSource {
        private final Node optCompleteServerCache;
        private final ViewCache viewCache;
        private final WriteTreeRef writes;

        public WriteTreeCompleteChildSource(WriteTreeRef writes2, ViewCache viewCache2, Node optCompleteServerCache2) {
            this.writes = writes2;
            this.viewCache = viewCache2;
            this.optCompleteServerCache = optCompleteServerCache2;
        }

        public Node getCompleteChild(ChildKey childKey) {
            CacheNode serverNode;
            CacheNode cacheNode;
            ChildKey childKey2 = childKey;
            CacheNode node = this.viewCache.getEventCache();
            if (node.isCompleteForChild(childKey2)) {
                return node.getNode().getImmediateChild(childKey2);
            }
            if (this.optCompleteServerCache != null) {
                new CacheNode(IndexedNode.from(this.optCompleteServerCache, KeyIndex.getInstance()), true, false);
                serverNode = cacheNode;
            } else {
                serverNode = this.viewCache.getServerCache();
            }
            return this.writes.calcCompleteChild(childKey2, serverNode);
        }

        public NamedNode getChildAfterChild(Index index, NamedNode child, boolean reverse) {
            return this.writes.calcNextNodeAfterPost(this.optCompleteServerCache != null ? this.optCompleteServerCache : this.viewCache.getCompleteServerSnap(), child, reverse, index);
        }
    }
}
