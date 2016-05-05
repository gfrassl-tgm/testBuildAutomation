package rational;

import java.math.BigInteger;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
/**
 * class which implements all functionality that is needed for the RationalTest.
 * allows work with rational numbers.
 * @author anonym
 *
 */
public class Rational implements Comparable<Rational> {

	private BigInteger numerator;
	private BigInteger denominator;
// Constructors---------------------------------------------
	public Rational(BigInteger n, BigInteger d) {
		int comp = d.compareTo(BigInteger.ZERO);
		if (comp == 0) {
			throw new RuntimeException("Rational Error: Cannot construct Rational with zero denominator");
		}
		this.numerator = n;
		this.denominator = d;
		this.normalize();
		this.shorten();
	}

	public Rational(long n, long d) {
		this(BigInteger.valueOf(n), BigInteger.valueOf(d));
	}

	public Rational(BigInteger n) {
		this(n, BigInteger.ONE);
	}

	public Rational(long n) {
		this(BigInteger.valueOf(n), BigInteger.ONE);
	}

	public Rational() {
		this(BigInteger.ZERO);
	}
//----------------------------------------------------------------
	
//Functionality
	public boolean isZero() {
		return this.numerator.compareTo(BigInteger.ZERO) == 0;
	}

	public boolean isPositive() {
		this.normalize();
		return this.numerator.compareTo(BigInteger.ZERO) > 0;
	}

	public boolean isNegative() {
		this.normalize();
		return this.numerator.compareTo(BigInteger.ZERO) < 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Rational))
			return false;
		Rational r = (Rational) obj;
		return this.compareTo(r) == 0;
	}

	/**
	 * compareTo to the second Rational
	 * 
	 * @param q
	 *            the second Rational
	 * @return 1 for greater than, 0 for equal and -1 for lower than
	 */
	public int compareTo(Rational q) {
		BigInteger a = this.numerator;
		BigInteger b = this.denominator;
		BigInteger c = q.numerator;
		BigInteger d = q.denominator;
		BigInteger e = (a.multiply(d)).subtract(b.multiply(c));
		return e.compareTo(BigInteger.ZERO);
	}

	public void normalize() {
		if (this.denominator.compareTo(BigInteger.ZERO) < 0) {
			this.denominator = this.denominator.abs();
			this.numerator = this.numerator.negate();
		}
	}

	public void negate() {
		this.numerator = this.numerator.negate();
		this.normalize();
	}

	public void invert() {
		BigInteger n = this.numerator;
		BigInteger d = this.denominator;
		int comp = n.compareTo(BigInteger.ZERO);
		if (comp == 0)
			throw new RuntimeException("Rational Error: Cannot invert zero");
		this.denominator = n;
		this.numerator = d;
		this.normalize();
	}

	public Rational add(Rational q) {
		Rational p = this.copy();
		return Rational.add(p, q);
	}

	public static Rational add(Rational p, Rational q) {
		BigInteger a = p.numerator;
		BigInteger b = p.denominator;
		BigInteger c = q.numerator;
		BigInteger d = q.denominator;
		BigInteger numerator = (a.multiply(d)).add(b.multiply(c));
		BigInteger denominator = b.multiply(d);
		Rational r = new Rational(numerator, denominator);
		r.normalize();
		r.shorten();
		return r;
	}

	public Rational sub(Rational q) {
		Rational p = this.copy();
		return Rational.sub(p, q);
	}

	public static Rational sub(Rational p, Rational q) {
		Rational s = q.copy();
		s.negate();
		return Rational.add(p, s);
	}

	public Rational mult(Rational q) {
		Rational p = this.copy();
		return Rational.mult(p, q);
	}

	public static Rational mult(Rational p, Rational q) {
		BigInteger a = p.numerator;
		BigInteger b = p.denominator;
		BigInteger c = q.numerator;
		BigInteger d = q.denominator;
		BigInteger gcd_ad = a.gcd(d);
		BigInteger gcd_bc = b.gcd(c);
		BigInteger numerator = (a.divide(gcd_ad)).multiply(c.divide(gcd_bc));
		BigInteger denominator = (b.divide(gcd_bc)).multiply(d.divide(gcd_ad));
		Rational r = new Rational(numerator, denominator);
		r.normalize();
		return r;
	}

	public Rational div(Rational q) {
		Rational p = this.copy();
		return Rational.div(p, q);
	}

	public static Rational div(Rational p, Rational q) {
		Rational s = q.copy();
		s.invert();
		return Rational.mult(p, s);
	}

	public Rational copy() {
		return Rational.valueOf(this.numerator, this.denominator);
	}

	public BigInteger getNumerator() {
		return numerator;
	}

	public BigInteger getDenominator() {
		return denominator;
	}

	public void setNumerator(BigInteger numerator) {
		this.numerator = numerator;
	}

	public void setDenominator(BigInteger denominator) {
		this.denominator = denominator;
	}

	public float floatValue() {
		return (this.numerator.floatValue() / denominator.floatValue());
	}

	public double doubleValue() {
		return (this.numerator.doubleValue() / denominator.doubleValue());
	}

	public void shorten() {
		BigInteger g = this.numerator.gcd(denominator);
		this.numerator = this.numerator.divide(g);
		this.denominator = this.denominator.divide(g);
	}

	public String toString() {
		Rational r = this.copy();
		StringBuffer ret = new StringBuffer();
		r.normalize();
		r.shorten();
		if (r.numerator.compareTo(BigInteger.ZERO) < 0) {
			ret.append('-');
			r.negate();
		}
		if (r.denominator.compareTo(BigInteger.ONE) == 0)
			ret.append(r.numerator.toString());
		else {
			BigInteger[] f = r.numerator.divideAndRemainder(r.denominator);
			if (f[0].compareTo(BigInteger.ZERO) > 0) {
				ret.append(f[0].toString()).append(' ').append(f[1].toString());
			} else
				ret.append(r.numerator.toString());
			ret.append('/').append(r.denominator.toString());

		}
		return ret.toString();
	}


	public static Rational valueOf(long n) {
		return Rational.valueOf(n, 1L);
	}

	
	public static Rational valueOf(long n, long d) {
		return Rational.valueOf(BigInteger.valueOf(n), BigInteger.valueOf(d));
	}

	public static Rational valueOf(BigInteger n, BigInteger d) {
		return new Rational(n, d);
	}

//-----------------------------------------------------------
/**
 * Main Method for testing
 * @param args
 */
	public static void main(String[] args) {
		Logger logger = LogManager.getLogger(Rational.class);
		logger.entry();
		Rational log1 = new Rational(25893, 51647);
		Rational log2 = new Rational(-46008L, 51647);
		Rational log3 = new Rational(62750931, 51808256L);
		logger.info(log1);
		logger.info(log2);
		logger.info(log3);
		Rational log4 = log2.mult(log3);
		logger.info(log4.getNumerator());
		logger.info(log4.getDenominator());
		logger.info(log4);
		log1.sub(log4);
		logger.error(log1);
		logger.error(log2);
		logger.error(log3);
		logger.error(log4);
		logger.exit();
	}
}