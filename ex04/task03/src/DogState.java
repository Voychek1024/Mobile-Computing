public interface DogState {
    public void showState();
}

class SoftlyState implements DogState {
    @Override
    public void showState() {
        System.out.println("Listen to the master");
    }
}

class MeetEnemyState implements DogState {
    @Override
    public void showState() {
        // TODO: REWRITE showState method
    }
}

class MeetFriendState implements DogState {
    @Override
    public void showState() {
        // TODO: REWRITE showState method
    }
}

class MeetAnotherDog implements DogState {
    @Override
    public void showState() {
        // TODO: REWRITE showState method
    }
}

class Dog {
    DogState state;
    public void show() {
        state.showState();
    }
    public void setState(DogState s) {
        state = s;
    }
}

class CheckDogState {
    public static void main(String[] args) {
        Dog yellowDog = new Dog();
        System.out.print("Dog ahead of master");
        yellowDog.setState(new SoftlyState());
        yellowDog.show();
        System.out.print("Dog meets enemy");
        yellowDog.setState(new MeetEnemyState());
        yellowDog.show();
        System.out.print("Dog meets friend");
        yellowDog.setState(new MeetFriendState());
        yellowDog.show();
        System.out.print("Dog meets dog");
        yellowDog.setState(new MeetAnotherDog());
        yellowDog.show();
    }
}