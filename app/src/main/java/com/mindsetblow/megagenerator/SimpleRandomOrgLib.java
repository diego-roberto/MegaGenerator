package com.mindsetblow.megagenerator;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.util.ArrayList;


/**
 * SimmpleRandomOrgLib() is a class to interface with the Random.org http api in a simple manor. It requires apache httpclient httpcore and logging
 * @author Charles Eakins
 * @author Diego Roberto ( randomNumberLottery() )
 *
 */
public class SimpleRandomOrgLib {
	public ArrayList<Integer> tmpArrayListInt;
	public ArrayList<String> tmpArrayListString;
	public String [] tmpArrayString;
	public String url;
	
	/**
	 * httpGet() returns the contents of a get line by line in an ArrayList	
	 * @param url String of url to get from
	 * @return String ArrayList
	 * @throws Exception
	 */
	public ArrayList<String> httpGet(String url) throws Exception{
		tmpArrayListString = new ArrayList();
		
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 7000);
		HttpConnectionParams.setSoTimeout(httpParams, 7000);
		HttpClient httpclient = new DefaultHttpClient(httpParams);
		HttpGet httpget = new HttpGet(url);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		
		String responseBody = httpclient.execute(httpget, responseHandler);
		httpclient.getConnectionManager().shutdown();
		
		tmpArrayString = responseBody.split("\n");
		int arrayCount = tmpArrayString.length;
		
		for (int i = 0;i < arrayCount;i++){
			tmpArrayListString.add(tmpArrayString[i]);
		}
		return tmpArrayListString;
	}
	/**
	 * randomNumberBaseTenInt() return an integer ArrayList
	 * @param amountOfNumbersToReturn Integer of how many numbers you want generated
	 * @param minNumber Integer of the minimum number to start with
	 * @param maxNumber Integer of the maximum number to start with
	 * @return Return an Integer ArrayList
	 * @throws Exception
	 */
	public ArrayList<Integer> randomNumberBaseTenInt(int amountOfNumbersToReturn,int minNumber, int maxNumber) throws Exception{
		
		tmpArrayListInt = new ArrayList();

		String url = "https://www.random.org/integers/?num="+Integer.toString(amountOfNumbersToReturn)+"&min="+Integer.toString(minNumber)+"&max="+Integer.toString(maxNumber)+"&col=1&base=10&format=plain&rnd=new";

		tmpArrayListString = this.httpGet(url);
		
		int arrayCount = tmpArrayListString.size();
		
		for (int i = 0;i < arrayCount;i++){
			tmpArrayListInt.add(Integer.parseInt(tmpArrayListString.get(i)));
		}

		return tmpArrayListInt;
		
	}
	/**
	 * randomNumberBaseTenString() return a String ArrayList
	 * @return Return an String ArrayList
	 * @throws Exception
	 */
	public ArrayList<String> randomNumberBaseTenString(int amountOfNumbersToReturn,int minNumber, int maxNumber) throws Exception{


		url = "https://www.random.org/integers/?num="+Integer.toString(amountOfNumbersToReturn)+"&min="+Integer.toString(minNumber)+"&max="+Integer.toString(maxNumber)+"&col=1&base=10&format=plain&rnd=new";
		
		return this.httpGet(url);
		
	}
	/**
	 * randomNumberSequence() returns a String ArrayList of an entire sequence of numbers in a random order
	 * @param minNumber String min number - accepts different base type numbers
	 * @param maxNumber String max number - accepts different base type numbers
	 * @return ArrayList cast as String
	 * @throws Exception
	 */
	public ArrayList<String> randomNumberSequence(String minNumber, String maxNumber) throws Exception {
		
		url = "https://www.random.org/sequences/?min="+minNumber+"&max="+maxNumber+"&col=1&format=plain&rnd=new";
		
		return this.httpGet(url);
	}
	/**
	 * randomNumberLottery() retorna um ArrayList de Integer com uma sequência de números de 1 a 60, padrão Mega
	 * @param quantidadeNumeros Integer da quantidade, selecionada no spinner (6 to 14)
	 * @return ArrayList cast como String
	 * @throws Exception
	 */
	public ArrayList<Integer> randomNumberLottery(int quantidadeNumeros) throws Exception{

		tmpArrayListInt = new ArrayList();

		String url = "https://www.random.org/quick-pick/?tickets=1&lottery="+quantidadeNumeros+"x60.0x0&format=plain";
		//essa URL é para tickets de loteria

		tmpArrayListString = this.httpGet(url);

		int arrayCount = tmpArrayListString.size();

		for (int i = 0;i < arrayCount;i++){
			tmpArrayListInt.add(Integer.parseInt(tmpArrayListString.get(i)));
		}

		return tmpArrayListInt;

	}

}
