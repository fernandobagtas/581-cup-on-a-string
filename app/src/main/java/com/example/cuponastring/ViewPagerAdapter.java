package com.example.cuponastring;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> contactList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    public void addContact(Fragment contact) {
        contactList.add(contact);
    }

    public void updateContact(String name, String phone, int position) {
        ContactA contact = (ContactA) contactList.get(position);
        contact.updateInfo(name, phone);
        contactList.set(position, contact);
    }

}
