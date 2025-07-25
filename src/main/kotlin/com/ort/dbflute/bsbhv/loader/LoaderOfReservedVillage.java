package com.ort.dbflute.bsbhv.loader;

import java.util.List;

import org.dbflute.bhv.*;
import com.ort.dbflute.exbhv.*;
import com.ort.dbflute.exentity.*;

/**
 * The referrer loader of RESERVED_VILLAGE as TABLE.
 * @author DBFlute(AutoGenerator)
 */
public class LoaderOfReservedVillage {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected List<ReservedVillage> _selectedList;
    protected BehaviorSelector _selector;
    protected ReservedVillageBhv _myBhv; // lazy-loaded

    // ===================================================================================
    //                                                                   Ready for Loading
    //                                                                   =================
    public LoaderOfReservedVillage ready(List<ReservedVillage> selectedList, BehaviorSelector selector)
    { _selectedList = selectedList; _selector = selector; return this; }

    protected ReservedVillageBhv myBhv()
    { if (_myBhv != null) { return _myBhv; } else { _myBhv = _selector.select(ReservedVillageBhv.class); return _myBhv; } }

    // ===================================================================================
    //                                                                    Pull out Foreign
    //                                                                    ================
    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public List<ReservedVillage> getSelectedList() { return _selectedList; }
    public BehaviorSelector getSelector() { return _selector; }
}
