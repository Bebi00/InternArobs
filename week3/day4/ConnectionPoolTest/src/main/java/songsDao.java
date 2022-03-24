import java.util.Collection;
import java.util.Optional;

public class songsDao implements DAO{
    @Override
    public Optional get(int id) {
        return Optional.empty();
    }

    @Override
    public Collection getAll() {
        return null;
    }

    @Override
    public int save(Object o) {
        return 0;
    }

    @Override
    public void update(Object o) {

    }

    @Override
    public void delete(Object o) {

    }
}
