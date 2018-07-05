
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int MAX_COUNT = 10000;
    private Resume[] storage = new Resume[MAX_COUNT];
    private int current; //pointer on new element and storage size

    public ArrayStorage() {
        current = 0;
    }

    void clear() {
        Arrays.fill(storage, 0, current - 1, null);
    }

    void save(Resume r) {
        storage[current] = r;
        current++;
    }

    Resume get(String uuid) {
        int index = getIndexById(uuid);
        return index == -1 ? null : storage[index];
    }

    private int getIndexById(String uuid) {
        for (int i = 0; i < current; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    void delete(String uuid) {

        int index = getIndexById(uuid);

        if (index >= 0 && index < MAX_COUNT - 1) {
            storage[index] = null;

            //shift elements
            System.arraycopy(storage, index + 1, storage, index, current - 1 - index);

            current--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, current);
    }

    int size() {
        return current;
    }
}
