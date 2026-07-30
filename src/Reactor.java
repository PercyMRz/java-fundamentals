public class Reactor {


    private final String name;
    private double temperature;
    private double flow;
    private static final double AUTOTHERMAL_TEMPERATURE = 650.0;
    private static final double MINIMUM_SAFE_FLOW = 250.0;

    public Reactor(String name, double temperature, double flow) {

        this.name = name;
        this.temperature = temperature;
        this.flow = flow;

    }

    public void adjustTemperature(double newTemperature) {
        this.temperature = newTemperature;
    }

    public void checkAutothermicPoint() {
        if (this.temperature >= AUTOTHERMAL_TEMPERATURE) {
            System.out.println("Temperature inside reactor " + this.name + ": " + this.temperature + ". Autothermal point reached");
        } else {
            System.out.println("Raise the temperature inside reactor " + this.name);
        }
    }

    public void flowTransfer(Reactor targetReactor, double flowToTransfer) {
        if (this.flow - flowToTransfer >= MINIMUM_SAFE_FLOW) {
            targetReactor.flow += flowToTransfer;
            this.flow -= flowToTransfer;
            System.out.println("Transferred " + flowToTransfer + " units from Reactor " + this.name + " to Reactor " + targetReactor.name);
        } else {

            System.out.println("Not enough flow to complete the transfer safely from reactor " + this.name);
        }

    }

    @Override
    public String toString() {
        return "Reactor{" +
                "name='" + name + '\'' +
                ", temperature=" + temperature +
                ", flow=" + flow +
                '}';
    }

}
