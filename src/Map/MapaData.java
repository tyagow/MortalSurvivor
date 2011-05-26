package Map;

import java.awt.Image;

public class MapaData {
	
	public String fileNameMap;
	public String fileNameWaypoints;
	public String fileNameObstaculos;
	public Image tileSet;
	
	public MapaData(String _fileNameMap, String _fileNameWaypoints, String _fileNameObstaculos, Image _tileSet){
		
		fileNameMap = _fileNameMap;
		fileNameWaypoints = _fileNameWaypoints;
		fileNameObstaculos = _fileNameObstaculos;
		tileSet=_tileSet;
	}
	
}
