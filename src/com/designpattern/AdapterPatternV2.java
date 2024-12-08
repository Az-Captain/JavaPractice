package com.designpattern;

import java.util.Scanner;

/**
 * 适配器模式2
 * @author Az
 * @date 2024/12/8
 */
public class AdapterPatternV2 {
    public static void main(String[] args) {
        Computer2 computer2 = new Computer2();
        USBCharger usbCharger = new USBCharger();
        TypeCToUSBAdapter adapter = new TypeCToUSBAdapter(usbCharger);
        
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int type = scanner.nextInt();
            if(type == 1){
                computer2.chargeWithTypeC();
            }else if(type == 2){
                adapter.chargeWithTypeC();
            }
        }
    }
}

interface USB2{
    void chargeWithUSB();
}

interface TypeC2{
    void chargeWithTypeC();
}

class Computer2 implements TypeC2{
    @Override
    public void chargeWithTypeC() {
        System.out.println("TypeC");
    }
}

class USBCharger implements USB2{
    @Override
    public void chargeWithUSB() {
        System.out.println("USB Adapter");
    }
}

class TypeCToUSBAdapter implements TypeC2{
    private USB2 usbDevice;

    public TypeCToUSBAdapter(USB2 usb2){
        this.usbDevice = usb2;
    }

    @Override
    public void chargeWithTypeC() {
        usbDevice.chargeWithUSB();
    }
}



