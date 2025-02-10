public class NumBox<T extends Number> {
	private T obj;

	public int incAndGet() {
		return obj.intValue() + 1;
	}
}
