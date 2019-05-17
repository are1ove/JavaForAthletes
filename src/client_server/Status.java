package client_server;

import javafx.collections.ObservableList;
import src.ObjectsTable;

import java.io.Serializable;

public class Status implements Serializable {
    final String message;
    final int errorCode; // 0 is success

    public Status(int code, String msg) {
        message = msg;
        errorCode = code;
    }

    public String getMessage() {
        return message;
    }
}

