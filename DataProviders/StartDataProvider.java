package DataProviders;

import org.testng.annotations.DataProvider;

public class StartDataProvider {
	@DataProvider(name="Links")
	public static Object [][] getLinks(){
		return new Object[][] {
			{"/html/body/div/section[2]/div/aside[1]/div[1]/div/div[2]/a", "https://barometr.info/stat/", "������ � �������� �������������"},
			{"/html/body/div/section[2]/div/aside[1]/div[4]/div/div[2]/a", "https://barometr.info/stat/", "������ � �������� �������������"},
			{"/html/body/div/section[3]/div/div[1]/div/div[9]/a", "https://barometr.info/news/n_yu/", "������� � ������������� � �������� �������������"},
			{"/html/body/div/section[3]/div/div[2]/div/div[9]/a", "https://barometr.info/news/n_nik/", "������� ������������ � �������� �������������"},
			{"/html/body/div/section[3]/div/div[3]/div/div[9]/a", "https://barometr.info/news/n_ykr/", "������� ������� � �������� �������������"},
			{"/html/body/div/section[2]/div/aside[2]/div[4]/div/div[2]/a", "https://barometr.info/news/foto/", "������������ � �������� �������������"},
			{"/html/body/div/section[2]/div/aside[2]/div[1]/div/div/a", "https://barometr.info/blog/", "���� � �������� �������������"}
		};
	}
	
	@DataProvider(name = "Coordinates")
	public static Object [][] getCoordinatesOfMainElements(){
		 return new Object [][] {
			 {"�������", "/html/body/div/header/div/div", 588, 5, 741, 5},
			 {"���������","/html/body/div/header/div/div/div[2]", 830, 13, 1059, 13},
			 {"���������� ���������","/html/body/div/header/div/div/div[1]", 737, 13, 966, 13},
			 {"������ ������� ��������� ����","/html/body/div/section[1]/div/div[1]", 1, 190, 39, 192},
			 {"������ ������� ��������� ����","/html/body/div/section[1]/div/div[2]", 327, 190,  429, 192},
			 {"������ ������� ��������� ����","/html/body/div/section[1]/div/div[3]", 653, 190, 819, 192},
			 {"�������� ���-�������","/html/body/div/section[2]/div/aside[1]/div[1]/h3", 690, 325, 919, 332},
			 {"��������� ���-��������","/html/body/div/section[2]/div/aside[1]/div[1]/div/div[1]",690, 367, 919, 374},
			 {"��������� �������","/html/body/div/section[2]/div/div/div/h3", 325, 363, 332, 402},
			 {"�������� �����","/html/body/div/section[2]/div/aside[2]/div[1]/h3",325, 690, 332, 919},
			 {"��������� ������������","/html/body/div/section[2]/div/aside[2]/div[4]/h3", 760, 690,1118, 919},
			 {"�������� �������������","/html/body/div/section[2]/div/aside[2]/div[4]/div/div[1]", 822, 690, 1181, 919},
			 {"��������� ���������","/html/body/div/section[2]/div/aside[2]/div[5]/h3", 1153, 690, 1512, 919},
			 {"���������� ���������","/html/body/div/section[2]/div/aside[2]/div[5]/div", 1194, 690, 1553, 919},
			 {"��������� �����","/html/body/div/section[2]/div/aside[2]/div[6]/h3", 1423, 690, 1782, 919},
			 {"���������� �����","/html/body/div/section[2]/div/aside[2]/div[6]/div", 1465, 690, 1824, 919},
			 {"��������� ������ �������","/html/body/div/section[2]/div/aside[2]/div[7]/h3", 1664, 690, 2021, 919},
			 {"����� ��� �����������","/html/body/div/section[2]/div/aside[2]/div[7]/div", 1705, 690, 2063, 919},
			 {"��������� �������","/html/body/div/section[2]/div/aside[1]/div[2]/h3", 690, 439, 919, 405},
			 {"������� �����","/html/body/div/section[2]/div/aside[1]/div[2]/div",690, 439, 919, 447},
			 {"��������� ������","/html/body/div/section[2]/div/aside[1]/div[3]/h3", 690, 704, 919, 1062},
			 {"���������� ������","/html/body/div/section[2]/div/aside[1]/div[3]/div", 690, 745, 919, 1103},
			 {"��������� �����","/html/body/div/section[2]/div/aside[1]/div[4]/h3", 690, 760, 919, 1118},
			 {"���������� �����","/html/body/div/section[2]/div/aside[1]/div[4]/div/div[1]/div[1]/div/div[2]/div", 1322, 10, 1334 -270},
			 {"��������� ����������","/html/body/div/section[2]/div/aside[1]/div[5]/h3", 1593, 10, 1605, 49},
			 {"��������� �������� �����","/html/body/div/section[3]/div/div[1]/h3", 3226, 10, 2574, 51},
			 {"������� �����","/html/body/div/section[3]/div/div[1]/div",  3267, 10, 2616, 51},
			 {"��������� �������� ������������","/html/body/div/section[3]/div/div[2]/h3", 3226, 336, 2574, 441},
			 {"������� ������������","/html/body/div/section[3]/div/div[2]/div", 3267, 336, 2616, 441},
			 {"��������� ������� ���� ������","/html/body/div/section[3]/div/div[3]/h3", 3226, 662, 2574, 831},
			 {"������� ���� ������","/html/body/div/section[3]/div/div[3]/div", 3267, 662, 2616, 831}
		};
	}
	
}
