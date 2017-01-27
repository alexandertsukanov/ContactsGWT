package com.mySampleApplication.client;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.view.client.ListDataProvider;
import com.mySampleApplication.entity.Contacts;

import java.util.List;

public class MySampleApplication implements EntryPoint {

    MySampleApplicationServiceAsync rpcService = GWT.create(MySampleApplicationService.class);
    CellTable<Contacts> table = new CellTable<Contacts>();
    ListDataProvider<Contacts> dataProvider = new ListDataProvider<Contacts>();
    final List<Contacts> contactsList = dataProvider.getList();

    Button addButton = new Button("Add", new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
            Contacts a = new Contacts();
            a.setName("Name");
            a.setSurname("Surname");
            a.setPhoneNumber("Phone");
            rpcService.saveUser(a, new AsyncCallback<Contacts>() {
                @Override
                public void onFailure(Throwable caught) {

                }
                @Override
                public void onSuccess(Contacts result) {

                }
            });
            dataProvider.getList().add(a);
            dataProvider.refresh();
            Window.Location.reload();
        }
    });

    Button saveButton = new Button("Save", new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
            for (Contacts c : contactsList){
                rpcService.updateUser(c, new AsyncCallback<Contacts>() {
                    @Override
                    public void onFailure(Throwable caught) {

                    }
                    @Override
                    public void onSuccess(Contacts result) {

                    }
                });
            }
            Window.alert("Contacts saved.");
        }
    });


    public void onModuleLoad() {

        Column<Contacts, Number> idColumn = new Column<Contacts, Number>(new NumberCell()) {
            @Override
            public Integer getValue(Contacts object) {
                return object.getId();
            }
        };


        Column<Contacts, String> nameColumn = new Column<Contacts, String>(
                new EditTextCell()) {
            @Override
            public String getValue(Contacts aUser) {
                return aUser.getName();
            }
        };

        Column<Contacts, String> surnameColumn = new Column<Contacts, String>(
                new EditTextCell()) {
            @Override
            public String getValue(Contacts aUser) {
                return aUser.getSurname();
            }
        };

        Column<Contacts, String> phoneColumn = new Column<Contacts, String>(
                new EditTextCell()) {
            @Override
            public String getValue(Contacts aUser) {
                return aUser.getPhoneNumber();
            }
        };

        Column<Contacts, String> deleteBtn = new Column<Contacts, String>(
                new ButtonCell()) {
            @Override
            public String getValue(Contacts c) {
                return "X";
            }
        };

        dataProvider.addDataDisplay(table);

        table.addColumn(nameColumn, "Name");
        table.addColumn(surnameColumn, "Surname");
        table.addColumn(phoneColumn, "Phone Number");
        table.addColumn(deleteBtn, "");


        nameColumn.setFieldUpdater(new FieldUpdater<Contacts, String>() {
            @Override
            public void update(int index, final Contacts object, String value) {
                object.setName(value);
                dataProvider.refresh();
            }
        });

        surnameColumn.setFieldUpdater(new FieldUpdater<Contacts, String>() {
            @Override
            public void update(int index, Contacts object, String value) {
                object.setSurname(value);
                dataProvider.refresh();
            }
        });

        phoneColumn.setFieldUpdater(new FieldUpdater<Contacts, String>() {
            @Override
            public void update(int index, Contacts object, String value) {
                object.setPhoneNumber(value);
                dataProvider.refresh();
            }
        });

        deleteBtn.setFieldUpdater(new FieldUpdater<Contacts, String>() {

            @Override
            public void update(int index, final Contacts object, String value) {
                boolean confirmed = Window.confirm("Do you want to delete " + object.getName()+ " contact?");
                if(confirmed) {
                    rpcService.deleteUser(object, new AsyncCallback<Contacts>() {
                        @Override
                        public void onFailure(Throwable caught) {

                        }

                        @Override
                        public void onSuccess(Contacts result) {

                        }
                    });
                    dataProvider.getList().remove(object);
                    dataProvider.refresh();
                }
               else{
                    return;
                }
            }
        });


        rpcService.getAllUsers(new AsyncCallback<List<Contacts>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Fail to get All Users.");
            }

            @Override
            public void onSuccess(List<Contacts> result) {
                for(Contacts contacts : result){
                    contactsList.add(contacts);
                }
            }
        });
        addButton.setStyleName("AddButton");
        saveButton.setStyleName("SaveButton");
        RootPanel.get().add(addButton);
        RootPanel.get().add(saveButton);
        RootPanel.get().add(table);

    }
}
