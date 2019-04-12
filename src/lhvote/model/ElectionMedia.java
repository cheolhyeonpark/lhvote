package lhvote.model;

public enum ElectionMedia {
	
	PC(1), 
	PHONE(2), 
	TEXT(3), 
	LOCAL(4);
	
	private int code;
	
	private ElectionMedia(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static ElectionMedia valueOf(int code) {
		for (ElectionMedia value : ElectionMedia.values()) {
			if (value.code == code) {
				return value;
			}
		}
		return null;
	}
	
	public static ElectionMedia[] getElectionMedias(String[] values) {
		ElectionMedia[] elecMedia = new ElectionMedia[values.length];
		for (int i = 0; i < values.length; i++) {
			elecMedia[i] = ElectionMedia.valueOf((values[i].toUpperCase()));
		}
		return elecMedia;
	}
	
	public static int[] getCodes(ElectionMedia[] values) {
		int[] codes = new int[values.length];
		for (int i = 0; i < values.length; i++) {
			codes[i] = values[i].getCode();
		}
		return codes;
	}
}
