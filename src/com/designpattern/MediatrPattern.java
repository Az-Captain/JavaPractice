package com.designpattern;

import java.util.*;

/**
 * 中介者模式
 * 用来降低多个对象和类之间的通信复杂性，属于行为型模式
 *
 * @author Az
 * @date 2025/1/29
 */
public class MediatrPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<String> userNames = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            userNames.add(scanner.next());
        }

        ChatRoomMediator mediator = new ChatRoomMediatorImpl();
        for (String name : userNames) {
            new ConcreteChaUser(name, mediator);
        }

        while (scanner.hasNext()) {
            String sender = scanner.next();
            String message = scanner.next();
            ChatUser user = mediator.getUsers().get(sender);
            Optional.ofNullable(user).ifPresent(it -> it.sendMessage(message));
        }
        scanner.close();

    }
}

/**
 * 中介者
 */
interface ChatRoomMediator {
    void sendMessage(String sender, String messsage);

    void addUser(ChatUser user);

    Map<String, ChatUser> getUsers();
}

/**
 * 具体中介者
 */
class ChatRoomMediatorImpl implements ChatRoomMediator {
    private Map<String, ChatUser> chatUserMap = new LinkedHashMap<>();

    @Override
    public void sendMessage(String sender, String messsage) {
        for (ChatUser user : chatUserMap.values()) {
            if (!user.getName().equals(sender)) {
                user.receiveMessage(sender, messsage);
            }
        }
    }

    @Override
    public void addUser(ChatUser user) {
        chatUserMap.put(user.getName(), user);
    }

    @Override
    public Map<String, ChatUser> getUsers() {
        return chatUserMap;
    }
}

/**
 * 抽象同事类
 */
abstract class ChatUser {
    private String name;
    private ChatRoomMediator mediator;
    private List<String> receivedMessages = new ArrayList<>();

    public ChatUser(String name, ChatRoomMediator mediator) {
        this.name = name;
        this.mediator = mediator;
        mediator.addUser(this);
    }

    public String getName() {
        return name;
    }

    public void sendMessage(String message) {
        mediator.sendMessage(name, message);
    }

    public abstract void receiveMessage(String name, String message);

    public List<String> getReceivedMessages() {
        return receivedMessages;
    }

    protected void addReceivedMessage(String message) {
        receivedMessages.add(message);
    }
}

/**
 * 具体同事类
 */
class ConcreteChaUser extends ChatUser {
    public ConcreteChaUser(String name, ChatRoomMediator mediator) {
        super(name, mediator);
    }

    @Override
    public void receiveMessage(String name, String message) {
        String receiveMessage = getName() + " received " + message;
        addReceivedMessage(message);
        System.out.println(receiveMessage);
    }
}


