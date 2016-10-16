package frame;
//소리파일 재생

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class SoundPlay {

    int EXTERNAL_BUFFER_SIZE = 128000;

	public  void sound(int index) {
		String arr = "";
		
		if(index==0){
			arr="sound/start.wav"; //대국을 시작합니다.
		}
		else if(index==1){
			arr="sound/end.wav"; //수고하셨습니다.
		}
		else if(index==2){
			arr="sound/pojin.wav"; //포진 선택 중입니다.
		}
		else if(index==3){
			arr="sound/jangun.wav";//장군.
		}
		else if(index==4){
			arr="sound/mungun.wav";//멍군.
		}
		else if(index==5){
			arr="sound/tak.wav";//탁.
		}
		
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new BufferedInputStream(
							new FileInputStream(arr)));
			AudioFormat audioFormat = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class,
					audioFormat);
			SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(audioFormat);
			line.start();

			int nBytesRead = 0;
			byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
			while (nBytesRead != -1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0) {
					line.write(abData, 0, nBytesRead);
				}
			}
			line.drain();
			line.close();
			audioInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}