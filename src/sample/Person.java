package sample;

public class Person {
          String name;
          String address;
          String number;
        public Person(String name, String address, String number) {
            this.name=name;
            this.address=address;
            this.number=number;
        }
        public String getAddress() {
            return this.address;
        }
        public String getName() {
            return this.name;
        }
        public String getNumber() {
            return this.number;
        }
        public void setName(String name){
            this.name=name;
        }
        public void setAddress(String address){
            this.address=address;
        }
        public void setNumber(String number){
            this.number=number;
        }
    }

