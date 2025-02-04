package Lab2.task1;

public class Top {
        private final String name;
        private final int value;

        public Top(String name, int value) {
            if (value < 0) {
                throw new IllegalArgumentException("Value cannot be negative");
            }
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Top{" +
                    "name='" + name + '\'' +
                    ", value=" + value +
                    '}';
        }
    }



