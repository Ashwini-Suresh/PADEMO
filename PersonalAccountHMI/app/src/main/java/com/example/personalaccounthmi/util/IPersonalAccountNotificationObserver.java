package com.example.personalaccounthmi.util;

public interface IPersonalAccountNotificationObserver {
    void notifyPersonalAccountChange(int propertyType, int data);
}
