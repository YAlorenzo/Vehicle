//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Car racing_car = new Car("racing_car");
        Car fire_truck = new Car("fire_truck");
        Car bus_car = new Car("bus_car");
        Car taxi_car = new Car("taxi_car");
        Car default_car = new Car("default_car");

        Bicycle scooter_bike = new Bicycle("scooter_bike");
        Bicycle default_bike = new Bicycle("default_bike");


        racing_car.start();
        fire_truck.start();
        bus_car.start();
        taxi_car.start();
        default_car.start();

        scooter_bike.start();
        default_bike.start();
    }

    public interface Vehicle {
        void start();
        void accelerate(int speed);
        void brake(int speed);

        default void drawScene(int roadLeft, int roadRight, String vehicleModel){
            String vehicle = vehicleModel;
            String partBefore = "-".repeat(roadLeft);
            String partAfter = "-".repeat(roadRight);

            String currentFrame = " \uD83C\uDFC1" + partBefore + vehicle + partAfter;
            System.out.print("\r" + currentFrame);
            System.out.flush();
        }
    }
    public enum VehicleSettings{
        MAX_SPEED(500),
        ROAD_LENGTH(100);

        private final int value;

        VehicleSettings(int value){
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    public enum VechileModel{
        FIRE_TRUCK("\uD83D\uDE92"),
        RACING_CAR("\uD83C\uDFCE\uFE0F"),
        BUS_CAR("\uD83D\uDE8C"),
        TAXI_CAR("\uD83D\uDE95"),
        DEFAULT_CAR("\uD83D\uDE97"),

        DEFAULT_BIKE("\uD83D\uDEB2"),
        SCOOTER_BIKE("\uD83D\uDEF4");

        private final String value;

        VechileModel(String value){
            this.value = value;
        }
        public String getVechileModel(){
            return value;
        }
    }

    public static class Car implements Vehicle {
        private int speed;
        private int roadLeft;
        private int roadRigth;
        private String vehicleName;
        private String vehicleModel;

        public Car(String vehicleName) {
            this.vehicleName = vehicleName;
            switch (vehicleName){
                case "fire_truck":
                    vehicleModel = VechileModel.FIRE_TRUCK.getVechileModel();
                    this.speed = 100;
                    break;
                case "racing_car":
                    vehicleModel = VechileModel.RACING_CAR.getVechileModel();
                    this.speed = 300;
                    break;
                case "bus_car":
                    vehicleModel = VechileModel.BUS_CAR.getVechileModel();
                    this.speed = 130;
                    break;
                case "taxi_car":
                    vehicleModel = VechileModel.TAXI_CAR.getVechileModel();
                    this.speed = 150;
                    break;
                case "default_car":
                    vehicleModel = VechileModel.DEFAULT_CAR.getVechileModel();
                    this.speed = 150;
                    break;
                default:
                    System.out.println("Error. not car name");
            }
        }
        @Override
        public void start() {
            int roadLength = VehicleSettings.ROAD_LENGTH.getValue();
            int roadLeft = roadLength - 1;
            int roadRight = 0;

            long startTime = System.nanoTime();
            for (int i = 0; i < roadLength; i++){
                drawScene(roadLeft, roadRight, vehicleModel);
                roadLeft -= 1;
                roadRight += 1;

                try {
                    if(roadRight == 25){
                        accelerate(150);
                    }
                    if(roadRight == 70){
                        brake(200);
                    }
                    Thread.sleep(VehicleSettings.MAX_SPEED.getValue() - speed);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            long endTime = System.nanoTime();
            long durationNano = endTime - startTime;
            double durationMillis = durationNano / 1_000_000_000.0;

            System.out.println(" The " + vehicleName + " drove in time " + durationMillis);
        }

        @Override
        public void accelerate(int speed) {
            this.speed += speed;
        }

        @Override
        public void brake(int speed) {
            this.speed -= speed;
        }
    }

    public static class Bicycle implements Vehicle{
        private int speed;
        private int roadLeft;
        private int roadRigth;
        private String vehicleName;
        private String vehicleModel;

        public Bicycle(String vehicleName) {
            this.vehicleName = vehicleName;
            switch (vehicleName){
                case "default_bike":
                    vehicleModel = VechileModel.DEFAULT_BIKE.getVechileModel();
                    this.speed = 50;
                    break;
                case "scooter_bike":
                    vehicleModel = VechileModel.SCOOTER_BIKE.getVechileModel();
                    this.speed = 80;
                    break;
                default:
                    System.out.println("Error. not car name");
            }
        }

        @Override
        public void start() {
            int roadLength = VehicleSettings.ROAD_LENGTH.getValue();
            int roadLeft = roadLength - 1;
            int roadRight = 0;



            long startTime = System.nanoTime();
            for (int i = 0; i < roadLength; i++){
                drawScene(roadLeft, roadRight, vehicleModel);
                roadLeft -= 1;
                roadRight += 1;

                try {
                    if(roadRight == 50){
                        accelerate(100);
                    }
                    if(roadRight == 70){
                        brake(70);
                    }
                    Thread.sleep(VehicleSettings.MAX_SPEED.getValue() - speed);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            long endTime = System.nanoTime();
            long durationNano = endTime - startTime;
            double durationMillis = durationNano / 1_000_000_000.0;
            System.out.println(" The " + vehicleName + " drove in time " + durationMillis);
        }

        @Override
        public void accelerate(int speed) {
            this.speed += speed;
        }
        @Override
        public void brake(int speed) {
            this.speed -= speed;
        }
    }
}
