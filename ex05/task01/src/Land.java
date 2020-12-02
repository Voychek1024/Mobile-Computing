public class Land {
    public static void main(String[] args) {
        Village.setWaterAmount(200); // TODO: call setWaterAmount and set to 200
        int leftWater = Village.waterAmount; // TODO: access waterAmount using class name
        System.out.println("In WELL: " + leftWater + " Liters.");
        Village zhao, ma;
        zhao = new Village("ZHAO");
        ma = new Village("MA");
        zhao.setPeopleNumber(80);
        ma.setPeopleNumber(120);
        zhao.drinkWater(50); // TODO: zhao drink 50
        leftWater = Village.lookWaterAmount(); // TODO: ma look water amount
        String name = ma.name;
        System.out.println(name + " found in WELL: " + leftWater + " Liters.");
        ma.drinkWater(100);
        leftWater = Village.lookWaterAmount(); // TODO: zhao look water amount
        name = zhao.name;
        System.out.println(name + " found in WELL: " + leftWater + " Liters.");
        int peopleNumber = zhao.getPeopleNumber();
        System.out.println("zhao POPULATION: " + peopleNumber);
        peopleNumber = ma.getPeopleNumber();
        System.out.println("ma POPULATION: " + peopleNumber);
    }
}
