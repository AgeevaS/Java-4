package com.company;

import somePackage.SomeBean;

public class Main {

    public static void main(String[] args) {
        SomeBean sb = (new Injector()).inject(new SomeBean());
        sb.foo();
    }
}
