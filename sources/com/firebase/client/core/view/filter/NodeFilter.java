package com.firebase.client.core.view.filter;

import com.firebase.client.core.Path;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.Index;
import com.firebase.client.snapshot.IndexedNode;
import com.firebase.client.snapshot.NamedNode;
import com.firebase.client.snapshot.Node;

public interface NodeFilter {

    public interface CompleteChildSource {
        NamedNode getChildAfterChild(Index index, NamedNode namedNode, boolean z);

        Node getCompleteChild(ChildKey childKey);
    }

    boolean filtersNodes();

    Index getIndex();

    NodeFilter getIndexedFilter();

    IndexedNode updateChild(IndexedNode indexedNode, ChildKey childKey, Node node, Path path, CompleteChildSource completeChildSource, ChildChangeAccumulator childChangeAccumulator);

    IndexedNode updateFullNode(IndexedNode indexedNode, IndexedNode indexedNode2, ChildChangeAccumulator childChangeAccumulator);

    IndexedNode updatePriority(IndexedNode indexedNode, Node node);
}
