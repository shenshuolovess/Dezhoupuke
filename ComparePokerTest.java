import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class ComparePokerTest {
	//9种等级的牌组1
	String[] poker= {"2H 3D 5S 9C KD","2H 2D 5S 8C KD",
	            "2H 2D 5S 5C KD","2H 2D 2S 8C 9D",
	            "2H 3D 4S 5C 6D","2H 3H 4H 5H 7H",
	            "2H 2D 2S 8C 8D","2H 2D 2S 2C 8D",
	            "2H 3H 4H 5H 6H"};
	//9种等级的牌组2
	String[] poker1= {"2H 3D 5S 9C AD","4H 4D 6S 8C AD",
            	"3H 3D 6S 6C AD","3H 3D 3S 8C AD",
            	"4H 5D 6S 7C 8D","4H 5H 6H 7H AH",
            	"8H 8D 8S AC AD","3H 3D 3S 3C AD",
            	"3H 4H 5H 6H 7H"};
	//一组牌的牌值
	String[] num= {"2359K","2258K","2255K","22289","23456",
					"23457","22288","22228","23456"};
	//一组牌的花色
	String[] huase= {"HDSCD","HDSCD","HDSCD","HDSCD","HDSCD",
					"HHHHH","HDSCD","HDSCD","HHHHH"};
	@Test
	//测试牌值相关的等级
	public void testGetCardslevel() {
		new ComparePoker();
		assertEquals(0,ComparePoker.getCardslevel(num[0],huase[0]));
		assertEquals(1,ComparePoker.getCardslevel(num[1],huase[1]));
		assertEquals(2,ComparePoker.getCardslevel(num[2],huase[2]));
		assertEquals(3,ComparePoker.getCardslevel(num[3],huase[3]));
		assertEquals(6,ComparePoker.getCardslevel(num[6],huase[6]));
		assertEquals(7,ComparePoker.getCardslevel(num[7],huase[7]));
	}

	@Test
	//测试花色值相关的等级
	public void testGetCardsColor() {
		new ComparePoker();
		assertEquals(5,ComparePoker.getCardsColor(num[5],huase[5]));
	}

	@Test
	//测试是否为顺子
	public void testTransition() {
		new ComparePoker();
		assertEquals(true,ComparePoker.transition(num[4]));
		assertEquals(true,ComparePoker.transition(num[8]));
		for(int i=0;i<poker.length;i++)
		{
			if(i!=4&&i!=8)
			assertEquals(false,ComparePoker.transition(num[i]));
		}
	}

	@Test
	//测试同等级两副牌的大小
	public void testValueBig() {
		new ComparePoker();
		for(int i=0;i<poker.length;i++)
		{
			assertEquals(1,ComparePoker.ValueBig(poker1[i],poker[i]));
		}
		for(int i=0;i<poker.length;i++)
		{
			assertEquals(-1,ComparePoker.ValueBig(poker[i],poker1[i]));
		}
		for(int i=0;i<poker.length;i++)
		{
			assertEquals(0,ComparePoker.ValueBig(poker[i],poker[i]));
		}
	}

	@Test(expected=Exception.class)
	//测试两副牌有某张相同牌则抛出异常
	public void testOverCards() throws Exception {
		new ComparePoker();
		ComparePoker.overCards(poker[0],poker[0]);
	}

	@Test(expected=Exception.class)
	//测试两副不同等级的牌的大小
	public void testCompareCards() throws Exception {
		new ComparePoker();
		for(int i=0;i<poker.length-1;i++)
		{
			assertEquals(1,ComparePoker.compareCards(poker1[i+1],poker[i]));
		}
		for(int i=0;i<poker.length-1;i++)
		{
			assertEquals(-1,ComparePoker.compareCards(poker[i],poker1[i+1]));
		}
		ComparePoker.compareCards(poker[1],poker[2]);//异常情况
		}
	
	@Test(expected=Exception.class)
	//测试一副牌返回的所有可能等级
	public void testGetCardSign() throws Exception {
		new ComparePoker();
		for(int i=0;i<poker.length;i++) {		
			assertEquals(i,ComparePoker.getCardSign(poker[i]));
		}
		ComparePoker.getCardSign("2H 2M 5S 8G KD");//异常情况
	}
	@Test
	//测试结果Black wins
	public void testPrint1()  {
		new ComparePoker();
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		ComparePoker.print(1);
		assertEquals("Black wins", outContent.toString());
		System.setOut(System.out);
	}
	@Test
	//测试结果White wins
	public void testPrint2()  {
		new ComparePoker();
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		ComparePoker.print(-1);
		assertEquals("White wins", outContent.toString());
		System.setOut(System.out);
	}
	@Test
	//测试结果Tie
	public void testPrint3()  {
		new ComparePoker();
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		ComparePoker.print(0);
		assertEquals("Tie", outContent.toString());
		System.setOut(System.out);
	}
	
}
