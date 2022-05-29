package org.example.bots.reader;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface MessageReader {

    void readMessage(Message message);
}
