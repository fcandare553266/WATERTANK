import javax.swing.JOptionPane;

abstract class waterTank {
    protected int capacity;
    protected int currentLevel;

    public waterTank(int capacity) {
        this.capacity = capacity;
        this.currentLevel = 0;
    }

    public void fillTank(int liters) {
        if (liters <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid input. Enter positive liters.");
            return;
        }
        int before = currentLevel;
        if (currentLevel + liters > capacity) {
            currentLevel = capacity;
        } else {
            currentLevel += liters;
        }
        int added = currentLevel - before;
        JOptionPane.showMessageDialog(null,
                added + " liters added. Current level: " + currentLevel + "/" + capacity);
    }

    public void useWater(int liters) {
        if (liters <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid input. Enter positive liters.");
            return;
        }
        int before = currentLevel;
        if (currentLevel - liters < 0) {
            currentLevel = 0;
        } else {
            currentLevel -= liters;
        }
        int used = before - currentLevel;
        JOptionPane.showMessageDialog(null,
                used + " liters used. Current level: " + currentLevel + "/" + capacity);
    }

    public String checkStatus() {
        if (currentLevel == 0) return "Tank is Empty!";
        if (currentLevel == capacity) return "Tank is Full!";
        return "Tank is In Use.";
    }
}

class HomeTank extends waterTank {
    public HomeTank() {
        super(200);
    }
}

class BuildingTank extends waterTank {
    public BuildingTank() {
        super(1000);
    }
}

class WaterTankProgram {
    public static void main(String[] args) {
        waterTank tank = null;
        String input = JOptionPane.showInputDialog(
                "Choose tank type:\n1 - HomeTank (200 liters)\n2 - BuildingTank (1000 liters)");
        if (input == null) return;
        int choice;
        try {
            choice = Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid choice. Exiting...");
            return;
        }

        if (choice == 1) {
            tank = new HomeTank();
        } else if (choice == 2) {
            tank = new BuildingTank();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid choice. Exiting...");
            return;
        }

        while (true) {
            String[] options = {"Fill Tank", "Use Water", "Check Status"};
            int action = JOptionPane.showOptionDialog(null, "Choose an action:",
                    "Water Tank Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (action == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Exiting...");
                return;
            }

            try {
                if (action == 0) {
                    String s = JOptionPane.showInputDialog("Enter liters to fill:");
                    if (s == null) continue;
                    int liters = Integer.parseInt(s.trim());
                    tank.fillTank(liters);
                } else if (action == 1) {
                    String s = JOptionPane.showInputDialog("Enter liters to use:");
                    if (s == null) continue;
                    int liters = Integer.parseInt(s.trim());
                    tank.useWater(liters);
                } else if (action == 2) {
                    JOptionPane.showMessageDialog(null, tank.checkStatus());
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
            }

            String status = tank.checkStatus();
            if (status.equals("Tank is Empty!") || status.equals("Tank is Full!")) {
                JOptionPane.showMessageDialog(null, status + "\nProgram ended.");
                break;
            }
        }
    }
}
