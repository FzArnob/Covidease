package com.firebase.client.core;

import com.firebase.client.annotations.NotNull;
import com.firebase.client.core.view.QuerySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ZombieEventManager implements EventRegistrationZombieListener {
    static final /* synthetic */ boolean $assertionsDisabled = (!ZombieEventManager.class.desiredAssertionStatus());
    private static ZombieEventManager defaultInstance;
    final HashMap<EventRegistration, List<EventRegistration>> globalEventRegistrations;

    static {
        ZombieEventManager zombieEventManager;
        new ZombieEventManager();
        defaultInstance = zombieEventManager;
    }

    private ZombieEventManager() {
        HashMap<EventRegistration, List<EventRegistration>> hashMap;
        new HashMap<>();
        this.globalEventRegistrations = hashMap;
    }

    @NotNull
    public static ZombieEventManager getInstance() {
        return defaultInstance;
    }

    /* JADX INFO: finally extract failed */
    public void recordEventRegistration(EventRegistration eventRegistration) {
        List list;
        List list2;
        EventRegistration registration = eventRegistration;
        HashMap<EventRegistration, List<EventRegistration>> hashMap = this.globalEventRegistrations;
        HashMap<EventRegistration, List<EventRegistration>> hashMap2 = hashMap;
        synchronized (hashMap) {
            try {
                List list3 = this.globalEventRegistrations.get(registration);
                if (list3 == null) {
                    new ArrayList();
                    list3 = list2;
                    List<EventRegistration> put = this.globalEventRegistrations.put(registration, list3);
                }
                boolean add = list3.add(registration);
                if (!registration.getQuerySpec().isDefault()) {
                    EventRegistration defaultRegistration = registration.clone(QuerySpec.defaultQueryAtPath(registration.getQuerySpec().getPath()));
                    List list4 = this.globalEventRegistrations.get(defaultRegistration);
                    if (list4 == null) {
                        new ArrayList();
                        list4 = list;
                        List<EventRegistration> put2 = this.globalEventRegistrations.put(defaultRegistration, list4);
                    }
                    boolean add2 = list4.add(registration);
                }
                registration.setIsUserInitiated(true);
                registration.setOnZombied(this);
            } catch (Throwable th) {
                Throwable th2 = th;
                HashMap<EventRegistration, List<EventRegistration>> hashMap3 = hashMap2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    private void unRecordEventRegistration(EventRegistration eventRegistration) {
        EventRegistration defaultRegistration;
        List<EventRegistration> registrationList;
        Throwable th;
        EventRegistration zombiedRegistration = eventRegistration;
        HashMap<EventRegistration, List<EventRegistration>> hashMap = this.globalEventRegistrations;
        HashMap<EventRegistration, List<EventRegistration>> hashMap2 = hashMap;
        synchronized (hashMap) {
            boolean found = false;
            try {
                List<EventRegistration> registrationList2 = this.globalEventRegistrations.get(zombiedRegistration);
                if (registrationList2 != null) {
                    int i = 0;
                    while (true) {
                        if (i >= registrationList2.size()) {
                            break;
                        } else if (registrationList2.get(i) == zombiedRegistration) {
                            found = true;
                            EventRegistration remove = registrationList2.remove(i);
                            break;
                        } else {
                            i++;
                        }
                    }
                    if (registrationList2.isEmpty()) {
                        List<EventRegistration> remove2 = this.globalEventRegistrations.remove(zombiedRegistration);
                    }
                }
                if ($assertionsDisabled || found || !zombiedRegistration.isUserInitiated()) {
                    if (!zombiedRegistration.getQuerySpec().isDefault() && (registrationList = this.globalEventRegistrations.get(defaultRegistration)) != null) {
                        int i2 = 0;
                        while (true) {
                            if (i2 >= registrationList.size()) {
                                break;
                            } else if (registrationList.get(i2) == zombiedRegistration) {
                                EventRegistration remove3 = registrationList.remove(i2);
                                break;
                            } else {
                                i2++;
                            }
                        }
                        if (registrationList.isEmpty()) {
                            List<EventRegistration> remove4 = this.globalEventRegistrations.remove((defaultRegistration = zombiedRegistration.clone(QuerySpec.defaultQueryAtPath(zombiedRegistration.getQuerySpec().getPath()))));
                        }
                    }
                    return;
                }
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                HashMap<EventRegistration, List<EventRegistration>> hashMap3 = hashMap2;
                throw th4;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public void zombifyForRemove(EventRegistration eventRegistration) {
        HashSet hashSet;
        EventRegistration registration = eventRegistration;
        HashMap<EventRegistration, List<EventRegistration>> hashMap = this.globalEventRegistrations;
        HashMap<EventRegistration, List<EventRegistration>> hashMap2 = hashMap;
        synchronized (hashMap) {
            try {
                List<EventRegistration> registrationList = this.globalEventRegistrations.get(registration);
                if (registrationList != null && !registrationList.isEmpty()) {
                    if (registration.getQuerySpec().isDefault()) {
                        new HashSet();
                        HashSet hashSet2 = hashSet;
                        for (int i = registrationList.size() - 1; i >= 0; i--) {
                            EventRegistration currentRegistration = registrationList.get(i);
                            if (!hashSet2.contains(currentRegistration.getQuerySpec())) {
                                boolean add = hashSet2.add(currentRegistration.getQuerySpec());
                                currentRegistration.zombify();
                            }
                        }
                    } else {
                        registrationList.get(0).zombify();
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                HashMap<EventRegistration, List<EventRegistration>> hashMap3 = hashMap2;
                throw th2;
            }
        }
    }

    public void onZombied(EventRegistration zombiedInstance) {
        unRecordEventRegistration(zombiedInstance);
    }
}
