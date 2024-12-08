package com.designpattern;

import java.util.Scanner;

/**
 * 适配器模式
 * @author Az
 * @date 2024/12/7
 */
public class AdapterPatter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int type = scanner.nextInt();
            if(type == 1){
                TypeC computer = new Computer();
                computer.chargeWithTypeC();
            }else if(type == 2){
                USB usbAdapter = new AdapterCharger();
                usbAdapter.charge();
            }

        }
    }
}

interface USB{
    void charge();
}

interface TypeC{
    void chargeWithTypeC();
}

class TypeCAdapter implements USB{
    private TypeC typeC;

    public TypeCAdapter(TypeC typeC){
        this.typeC = typeC;
    }

    @Override
    public void charge() {
        typeC.chargeWithTypeC();
    }
}

class Computer implements TypeC{

    @Override
    public void chargeWithTypeC() {
        System.out.println("TypeC");
    }
}

class AdapterCharger implements USB{
    @Override
    public void charge() {
        System.out.println("USB Adapter");
    }
}

