package p1;

public class UOAUtilities {

    private int next;
    private int size;
    private StudentListing[] data;

    public UOAUtilities() {
        next = 0;
        size = 100;
        data = new StudentListing[size];
    }//end of constructor

    public UOAUtilities(int s) {
        next = 0;
        data = new StudentListing[s];
        size = s;
    }//end of constructor

//    private boolean expand() {
//        size *= 2;
//        StudentListing[] temp = new StudentListing[size];
//        System.arraycopy(data, 0, temp, 0, next);
//        data = temp;
//        return true;
//    }
    
       private boolean expand() {
        StudentListing[] temp = data;
        data = new StudentListing[size * 2];
        if(data == null) {
            data = temp;
            return false;
        } else {
            System.arraycopy(temp, 0, data, 0, temp.length);
            size = data.length;
            return true;
        }
    }

    public boolean insert(StudentListing newStudentListing) {
        if (next == size) // the structure is full
        {
            if (!expand()) {
                return false;
            }
        }
        data[next] = newStudentListing.deepCopy();  // store a deep copy of the client’s student
        if (data[next] == null) {
            return false;
        }
        next = next + 1; // prepare for the next insert
        return true;
    }// end of insert method

    public StudentListing fetch(String targetKey) {
        StudentListing student;
        StudentListing temp;
        // access the student using a sequential search
        int i = 0;
        while (i < next && !(data[i].compareTo(targetKey) == 0)) {
            i++;
        }
        if (i == next) // student not found
        {
            return null;
        }
        //deep copy the student's information into the client's student
        student = data[i].deepCopy();
        // move the student up one position in the array, unless it is the first student
        if (i != 0) // bubble-up accessed student
        {
            temp = data[i - 1];
            data[i - 1] = data[i];
            data[i] = temp;
        }
        return student;
    } // end of fetch method

    public boolean delete(String targetKey) {// access the student using a sequential search
        int i = 0;
        while (i < next && !(data[i].compareTo(targetKey) == 0)) {
            i++;
        }
        if (i == next) // student not found
        {
            return false;
        }
        //move the last student into the deleted student's position
        data[i] = data[next - 1];
        data[next - 1] = null;
        next = next - 1;
        return true; // student found and deleted
    }//end of the delete method

    public boolean update(String targetKey, StudentListing newStudentListing) {
        if (delete(targetKey) == false) // student not in the structure
        {
            return false;
        } else if (insert(newStudentListing) == false) // insufficient memory
        {
            return false;
        } else {
            return true;  // student found and updated
        }
    }// end of update method

    public void showAll() {
        for (int i = 0; i < next; i++) {
            System.out.println(data[i].toString());
        }
    }// end showAll method
}//end of class UOAUtilities

