public class OffByN implements CharacterComparator{
    private int offby;

    public OffByN(int N) {
        offby = N;
    }

    @Override
    public boolean equalChars(char x, char y){
        return x - y == offby || y - x == offby;
    }
}
