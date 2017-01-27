package com.mySampleApplication.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mySampleApplication.entity.Contacts;

import java.util.List;

public interface MySampleApplicationServiceAsync {


    void getAllUsers(AsyncCallback<List<Contacts>> async);

    void saveUser(Contacts user, AsyncCallback<Contacts> async);

    void deleteUser(Contacts user, AsyncCallback<Contacts> async);

    void updateUser(Contacts user, AsyncCallback<Contacts> async);
}
