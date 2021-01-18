package havis.net.rest.shared.data;

public class SerializableValue<T> {
	private T value;
	
	public SerializableValue() {
		
	}
	
	public SerializableValue(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj.equals(value);
	}
}
