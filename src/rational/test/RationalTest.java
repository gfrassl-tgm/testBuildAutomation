package rational.test;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rational.Rational;

public class RationalTest {
	private Rational a,b;
	
	@Before
	public void setUp() throws Exception {
		a=null;
		b=null;
	}

	@After
	public void tearDown() throws Exception {
		a=null;
		b=null;
	}

	@Test
	public final void testRationalBigIntegerBigInteger() {
		long ln=3L;
		long ld=4L;
		BigInteger n=BigInteger.valueOf(ln);
		BigInteger d=BigInteger.valueOf(ld);
		a=new Rational(new BigInteger("3"),new BigInteger("4"));
		BigInteger actual1=a.getNumerator();
		BigInteger actual2=a.getDenominator();
		assertEquals(actual1, n);
		assertEquals(actual2, d);
	}
	
	@Test(expected=RuntimeException.class)
	public final void testRationalBigIntegerBigIntegerRuntimeException() {
		a=new Rational(new BigInteger("3"),new BigInteger("0"));
		fail("not reachable!");
	}

	
	@Test
	public final void testRationalLongLong() {
		long ln=3L;
		long ld=4L;
		a=new Rational(ln,ld);
		BigInteger actual1=a.getNumerator();
		BigInteger actual2=a.getDenominator();
		assertEquals(actual1, BigInteger.valueOf(ln));
		assertEquals(actual2, BigInteger.valueOf(ld));
	}

	@Test(expected=RuntimeException.class)
	public final void testRationalLongLongRuntimeException() {
		a=new Rational(4l,0);
		fail("not reachable!");
	}

	@Test
	public final void testRationalBigInteger() {
		long l=4L;
		BigInteger n=BigInteger.valueOf(l);
		a=new Rational(new BigInteger("4"));
		BigInteger actual1=a.getNumerator();
		BigInteger actual2=a.getDenominator();
		assertEquals(actual1, n);
		assertEquals(actual2, BigInteger.ONE);	

	}

	@Test
	public final void testRationalLong() {
		long n=4L;
		a=new Rational(n);
		BigInteger actual1=a.getNumerator();
		BigInteger actual2=a.getDenominator();
		assertEquals(actual1, BigInteger.valueOf(n));
		assertEquals(actual2, BigInteger.ONE);	
	}

	@Test
	public final void testRational() {
		a=new Rational();
		BigInteger actual1=a.getNumerator();
		BigInteger actual2=a.getDenominator();
		assertEquals(actual1, BigInteger.ZERO);
		assertEquals(actual2, BigInteger.ONE);
	}

	@Test
	public final void testIsZero() {
		a= new Rational();
		assertTrue(a.isZero());
	}

	@Test
	public final void testIsNotPositive() {
		a = new Rational(-1L,2L);
		assertFalse(a.isPositive());
	}

	@Test
	public final void testIsNotNegative() {
		a = new Rational(1L,2L);
		assertFalse(a.isNegative());
	}

	@Test
	public final void testIsNotZero() {
		a= new Rational(1L);
		assertFalse(a.isZero());
	}

	@Test
	public final void testIsPositive() {
		a = new Rational(1L,2L);
		assertTrue(a.isPositive());
	}

	@Test
	public final void testIsNegative() {
		a = new Rational(-1L,2L);
		assertTrue(a.isNegative());
	}

	@Test
	public final void testNotEqualString() {
		a = new Rational(-1L,2L);
		assertFalse(a.equals("No"));
	}
	
	@Test
	public final void testNotEqualRational() {
		a = new Rational(-1L,2L);
		assertFalse(a.equals(new Rational(1L,2L)));
	}	
	
	@Test
	public final void testCompareToGreatherZero() {
		a=new Rational(new BigInteger("3"),new BigInteger("4"));
		b = new Rational(1L,2L);
		assertTrue(a.compareTo(b)>0);
	}
	
	@Test
	public final void testCompareToLowerZero() {
		a=new Rational(new BigInteger("3"),new BigInteger("4"));
		b = new Rational(1L,2L);
		assertTrue(b.compareTo(a)<0);
	}
	
	@Test
	public final void testCompareToZero() {
		a=new Rational(new BigInteger("3"),new BigInteger("4"));
		b = new Rational(3L,4L);
		assertTrue(a.compareTo(b)==0);
	}
	
	@Test
	public final void testNormalize() {
		a=new Rational(new BigInteger("-3"),new BigInteger("-4"));
		b = new Rational(3L,4L);
		a.normalize();
		assertEquals(a, b);

	}

	@Test
	public final void testNegate() {
		a=new Rational(new BigInteger("-3"),new BigInteger("-4"));
		b = new Rational(-3L,4L);
		a.negate();
		assertEquals(a, b);
	}

	@Test
	public final void testInvert() {
		a=new Rational(new BigInteger("3"),new BigInteger("4"));
		b = new Rational(4L,3L);
		a.invert();
		assertEquals(a, b);
	}

	@Test(expected=RuntimeException.class)
	public final void testInvertRuntimeException() {
		a=new Rational();
		a.invert();
		fail("not reachable!");
	}
	
	@Test
	public final void testAddRational() {
		a=new Rational(new BigInteger("3"),new BigInteger("4"));
		b = new Rational(4L,3L);
		Rational c=new Rational(25L,12L);
		Rational d=a.add(b);
		assertEquals(c, d);
	}

	@Test
	public final void testAddRationalRational() {
		a=new Rational(new BigInteger("3"),new BigInteger("4"));
		b = new Rational(4L,3L);
		Rational c=new Rational(25L,12L);
		Rational d=Rational.add(a,b);
		assertEquals(c, d);
	}

	@Test
	public final void testSubRational() {
		a=new Rational(new BigInteger("3"),new BigInteger("4"));
		b = new Rational(1L,2L);
		Rational c=new Rational(1L,4L);
		Rational d=a.sub(b);
		assertEquals(c, d);	
	}

	@Test
	public final void testSubRationalRational() {
		a=new Rational(new BigInteger("3"),new BigInteger("4"));
		b = new Rational(1L,2L);
		Rational c=new Rational(1L,4L);
		Rational d=Rational.sub(a,b);
		assertEquals(c, d);	
	}

	@Test
	public final void testMultRational() {
		a=new Rational(new BigInteger("3"),new BigInteger("4"));
		b = new Rational(1L,2L);
		Rational c=new Rational(3L,8L);
		Rational d=a.mult(b);
		assertEquals(c, d);	
	}

	@Test
	public final void testMultRationalRational() {
		a=new Rational(new BigInteger("3"),new BigInteger("4"));
		b = new Rational(1L,2L);
		Rational c=new Rational(3L,8L);
		Rational d=Rational.mult(a,b);
		assertEquals(c, d);
	}

	@Test
	public final void testDivRational() {
		a=new Rational(new BigInteger("3"),new BigInteger("4"));
		b = new Rational(1L,2L);
		Rational c=new Rational(6L,4L);
		Rational d=a.div(b);
		assertEquals(c, d);
	}

	@Test
	public final void testDivRationalRational() {
		a=new Rational(new BigInteger("3"),new BigInteger("4"));
		b = new Rational(1L,2L);
		Rational c=new Rational(6L,4L);
		Rational d=Rational.div(a,b);
		assertEquals(c, d);
	}

	@Test
	public final void testCopy() {
		a=new Rational(new BigInteger("3"),new BigInteger("4"));
		b = a.copy();
		assertEquals(a, b);
		assertFalse(a==b);
	}

	@Test
	public final void testGetNumerator() {
		a=new Rational(new BigInteger("3"),new BigInteger("4"));
		BigInteger actual = a.getNumerator();
		BigInteger expected = BigInteger.valueOf(3);
		assertEquals(actual, expected);
	}

	@Test
	public final void testGetDenominator() {
		a=new Rational(new BigInteger("3"),new BigInteger("4"));
		BigInteger actual = a.getDenominator();
		BigInteger expected = BigInteger.valueOf(4);
		assertEquals(actual, expected);
	}

	@Test
	public final void testSetNumerator() {
		BigInteger d=new BigInteger("4");
		a=new Rational(BigInteger.ONE,new BigInteger("3"));
		b=new Rational(d,new BigInteger("3"));
		a.setNumerator(d);
		assertEquals(a,b);
	}

	@Test
	public final void testSetDenominator() {
		BigInteger d=new BigInteger("4");
		a=new Rational(new BigInteger("3"),BigInteger.ONE);
		b=new Rational(new BigInteger("3"),d);
		a.setDenominator(d);
		assertEquals(a,b);
	}

	@Test
	public final void testFloatValue() {
		a=new Rational(new BigInteger("3"),new BigInteger("4"));
		float actual=a.floatValue();
		float expected=(float)(3.0/4);
		assertEquals(actual,expected,0.001);
	}

	@Test
	public final void testDoubleValue() {
		a=new Rational(new BigInteger("3"),new BigInteger("4"));
		double actual=a.doubleValue();
		double expected=3.0/4;
		assertEquals(actual,expected,0.001);
	}

	@Test
	public final void testShorten() {
		a=new Rational(new BigInteger("3"),new BigInteger("4"));
		b=new Rational(new BigInteger("12"),new BigInteger("16"));
		b.shorten();
		assertEquals(a.getDenominator(), b.getDenominator());
		assertEquals(a.getNumerator(), b.getNumerator());
	}

	@Test
	public final void testToStringTrueRational() {
		a=new Rational(new BigInteger("3"),new BigInteger("4"));
		String actual=a.toString();
		String expected="3/4";
		assertEquals(actual,expected);
	}

	@Test
	public final void testToStringBigInteger() {
		a=new Rational(new BigInteger("3"),BigInteger.ONE);
		String actual=a.toString();
		String expected="3";
		assertEquals(actual,expected);
	}
	
	@Test
	public final void testToStringPseudoRational() {
		a=new Rational(new BigInteger("3"),new BigInteger("2"));
		String actual=a.toString();
		String expected="1 1/2";
		assertEquals(actual,expected);
	}

	@Test
	public final void testToStringNegativePseudoRational() {
		a=new Rational(new BigInteger("-3"),new BigInteger("2"));
		String actual=a.toString();
		String expected="-1 1/2";
		assertEquals(actual,expected);
	}	
	
	@Test
	public final void testValueOfLong() {
		a=new Rational(4);
		b=Rational.valueOf(4);
		assertEquals(a, b);
	}

	@Test
	public final void testValueOfLongLong() {
		BigInteger n=new BigInteger("-3");
		BigInteger d=new BigInteger("2");
		a=new Rational(n,d);
		b=Rational.valueOf(-3,2);
		assertEquals(a, b);
	}

	@Test
	public final void testValueOfBigIntegerBigInteger() {
		BigInteger n=new BigInteger("-3");
		BigInteger d=new BigInteger("2");
		a=new Rational(n,d);
		b=Rational.valueOf(n,d);
		assertEquals(a, b);
	}
	
}
