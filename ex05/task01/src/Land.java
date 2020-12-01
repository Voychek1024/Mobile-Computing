public class Land {
    public static void main(String[] args) {
        // TODO: call setWaterAmount and set to 200
        int leftWater = 2; // TODO: access waterAmount using class name
        System.out.println("In WELL: " + leftWater + "Liters");
        Village zhao, ma;
        zhao = new Village("ZHAO");
        ma = new Village("MA");
        zhao.setPeopleNumber(80);
        ma.setPeopleNumber(120);
        // TODO: zhao drink 50
        leftWater = 4; // TODO: ma look water amount
        String name = ma.name;
        System.out.println(name + "find in WELL: " + leftWater + "Liters");
        ma.drinkWater(100);
        leftWater = 5; // TODO: zhao look water amount
        name = zhao.name;
        System.out.println(name + "find in WELL: " + leftWater + "Liters");
        int peopleNumber = zhao.getPeopleNumber();
        System.out.println("zhao POPULATION: " + peopleNumber);
        peopleNumber = ma.getPeopleNumber();
        System.out.println("ma POPULATION: " + peopleNumber);
    }
}
