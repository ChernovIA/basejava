
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int MAX_COUNT = 10000;
    private Resume[] storage = new Resume[MAX_COUNT];
    private int current; //pointer on new element and storage size

    public ArrayStorage(){
        current = 0;
    }

    void clear() {
        Arrays.fill(storage,null);
        current = 0;
    }

    void save(Resume r) {
        if (current == MAX_COUNT){
            current = MAX_COUNT - 1;
        }
        storage[current] = r;
        current++;
    }

    Resume get(String uuid) {
        int index = getIndexById(uuid);
        return index==-1?null:storage[index];
    }

    private int getIndexById(String uuid){
        for(int i = 0; i < current; i++){
            if (storage[i].uuid.equals(uuid)){
                return i;
            }
        }
        return -1;
    }

    void delete(String uuid) {

        int index = getIndexById(uuid);

        if (index >= 0 && index < MAX_COUNT - 1){
            storage[index] = null;

            //shift elements
            for(int j = index; j < current-1; j++){
                storage[j] = storage[j+1];
            }

            current--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage,current);
    }

    int size() {
        return current;
    }
}
