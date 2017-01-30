package com.mySampleApplication.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mySampleApplication.entity.Contacts;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public interface MySampleApplicationServiceAsync {

    void getAllUsers(AsyncCallback<List<Contacts>> async);

    void deleteUser(Contacts user, AsyncCallback<Contacts> async);

    void updateUser(List<Contacts> user, AsyncCallback <Contacts> async);
}
