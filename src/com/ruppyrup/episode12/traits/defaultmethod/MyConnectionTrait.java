package com.ruppyrup.episode12.traits.defaultmethod;

import com.ruppyrup.episode12.traits.abstractclass.MyConnection;

public interface MyConnectionTrait {

    default MyConnection refresh() {
        return new MyConnection();
    }

}
