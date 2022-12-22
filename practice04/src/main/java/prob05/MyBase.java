package prob05;

public class MyBase extends Base {
	// 다른 메소드 수정 없이 MyBase 구현하기
	@Override
	public void service(String state){
		if( state.equals( "낮" ) ) {
			day();
		} else if (state.equals( "오후" )) {
			afternoon();
		} else {
			night();
		}
	}
	
	private void afternoon() {
		System.out.println("오후도 낮과 마찬가지로 일해야 합니다.");
	}

	public void day(){
		System.out.println("낮에는 열심히 일하자!");
	}
}
