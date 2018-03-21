import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class WineQuality {
	
	Dimension dimension=new Dimension();
	int totalCount=0;
	
	HashMap<String,Integer> fixedAcidityCount=new HashMap<String,Integer>();
	HashMap<String,Integer> volatileAcidityCount=new HashMap<String,Integer>();
	HashMap<String,Integer>citricAcidCount=new HashMap<String,Integer>();
	HashMap<String,Integer> residualSugarCount=new HashMap<String,Integer>();
	HashMap<String,Integer> chloridesCount=new HashMap<String,Integer>();
	HashMap<String,Integer> freeSulpharDioxideCount=new HashMap<String,Integer>();
	HashMap<String,Integer> totalSulpharDioxideCount=new HashMap<String,Integer>();
	HashMap<String,Integer> densityCount=new HashMap<String,Integer>();
	HashMap<String,Integer> phCount=new HashMap<String,Integer>();
	HashMap<String,Integer> sulphatesCount=new HashMap<String,Integer>();
	HashMap<String,Integer> alcoholCount=new HashMap<String,Integer>();
	HashMap<String,Integer> qualityCount=new HashMap<String,Integer>();
	
	HashMap<String, String> fAcidityNI = new HashMap<String, String>();
	HashMap<String, String> vAcidityNI = new HashMap<String, String>();
	HashMap<String, String> citricNI = new HashMap<String, String>();
	HashMap<String, String> rSugarNI = new HashMap<String, String>();
	HashMap<String, String> chlorideNI = new HashMap<String, String>();
	HashMap<String, String> fSulfurNI = new HashMap<String, String>();
	HashMap<String, String> tSulfurNI = new HashMap<String, String>();
	HashMap<String, String> densityNI = new HashMap<String, String>();
	HashMap<String, String> pHNI = new HashMap<String, String>();
	HashMap<String, String> sulphateNI = new HashMap<String, String>();
	HashMap<String, String> alcoholNI = new HashMap<String, String>();
	HashMap<String, String> qualityNI = new HashMap<String, String>();
	
	HashMap<Integer, String> fixedAcidityIce = new HashMap<Integer, String>();
	HashMap<Integer, String> volatileAcidityIce = new HashMap<Integer, String>();
	HashMap<Integer, String> citricAcidIce = new HashMap<Integer, String>();
	HashMap<Integer, String> residualSugarIce = new HashMap<Integer, String>();
	HashMap<Integer, String> chloridesIce = new HashMap<Integer, String>();
	HashMap<Integer, String> freeSulfurIce = new HashMap<Integer, String>();
	HashMap<Integer, String> totalSulfurIce = new HashMap<Integer, String>();
	HashMap<Integer, String> densityIce = new HashMap<Integer, String>();
	HashMap<Integer, String> pHIce = new HashMap<Integer, String>();
	HashMap<Integer, String> sulphatesIce = new HashMap<Integer, String>();
	HashMap<Integer, String> alcoholIce = new HashMap<Integer, String>();
	HashMap<Integer, String> qualityIce = new HashMap<Integer, String>();
	
	HashMap<Integer, ArrayList<String>> table = new HashMap<Integer, ArrayList<String>>();
	HashMap<ArrayList<String>, Integer> reduceTable = new HashMap<ArrayList<String>, Integer>();
	
	public void readFile(){
		
		String fileName=new String("/Users/bhargava/Desktop/winequality-red.csv");
		String line=null;
		
		try{
		FileReader fileReader=new FileReader(fileName);
		BufferedReader br=new BufferedReader(fileReader);
		int index=0;
		//String line=null;
		while((line=br.readLine())!=null){
			String[] sarray=line.split(";");
			//System.out.println("array : "+sarray[1]);
			index++;
			if(index>1){
			dimension.fixedAcidity.put(index-2, sarray[0]);
			dimension.volatileAcidity.put(index-2, sarray[1]);
			dimension.citricAcid.put(index-2, sarray[2]);
			dimension.residualSugar.put(index-2, sarray[3]);
			dimension.chlorides.put(index-2, sarray[4]);
			dimension.freeSulpharDioxide.put(index-2, sarray[5]);
			dimension.totalSulpharDioxide.put(index-2, sarray[6]);
			dimension.density.put(index-2, sarray[7]);
			dimension.ph.put(index-2, sarray[8]);
			dimension.sulphates.put(index-2, sarray[9]);
			dimension.alcohol.put(index-2, sarray[10]);
			dimension.quality.put(index-2, sarray[11]);
			
			}
			
		}
		//System.out.println(dimension.fixedAcidity);
		br.close();
		}
		catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
		catch(IOException e){
			System.out.println("Error in reading file"+fileName+"'");
		}
		
	}
	
	public HashMap<String,Integer> count(HashMap<Integer,String> map1, HashMap<String,Integer> map2){
		int count=1;
		
		for(int i=0;i<map1.size();i++){
			if(!map2.containsKey(map1.get(i))){
				map2.put(map1.get(i), count);
			}
			else{
				int val=map2.get(map1.get(i));
				map2.put(map1.get(i), val+1);
			}
		}
		return map2;
	}
	
	public void updateCount(){
		fixedAcidityCount=count(dimension.fixedAcidity,fixedAcidityCount);
		volatileAcidityCount=count(dimension.volatileAcidity,volatileAcidityCount);
		citricAcidCount=count(dimension.citricAcid,citricAcidCount);
		residualSugarCount=count(dimension.residualSugar,residualSugarCount);
		chloridesCount=count(dimension.chlorides,chloridesCount);
		freeSulpharDioxideCount=count(dimension.freeSulpharDioxide,freeSulpharDioxideCount);
		totalSulpharDioxideCount=count(dimension.totalSulpharDioxide,totalSulpharDioxideCount);
		densityCount=count(dimension.density,densityCount);
		phCount=count(dimension.ph,phCount);
		sulphatesCount=count(dimension.sulphates,sulphatesCount);
		alcoholCount=count(dimension.alcohol,alcoholCount);
		//qualityCount=count(dimension.quality,qualityCount);
		
	}
	
	public HashMap<String,String> checkIceberg(HashMap<String,Integer> map1,HashMap<String,String> map2,int aprioric){
		for(Map.Entry<String,Integer> entry:map1.entrySet()){
			if(entry.getValue()<aprioric){
				map2.put(entry.getKey(),"*");
			}
		}
		return map2;
	}
	
	public void updateNI(int aprioric){
		fAcidityNI = checkIceberg(fixedAcidityCount, fAcidityNI, aprioric);
		vAcidityNI =checkIceberg(volatileAcidityCount, vAcidityNI, aprioric);
		citricNI = checkIceberg(citricAcidCount, citricNI, aprioric);
		rSugarNI = checkIceberg(residualSugarCount, rSugarNI, aprioric);
		chlorideNI = checkIceberg(chloridesCount, chlorideNI, aprioric);
		fSulfurNI = checkIceberg(freeSulpharDioxideCount, fSulfurNI, aprioric);
		tSulfurNI = checkIceberg(totalSulpharDioxideCount, tSulfurNI, aprioric);
		densityNI = checkIceberg(densityCount, densityNI, aprioric);
		pHNI = checkIceberg(phCount, pHNI, aprioric);
		sulphateNI = checkIceberg(sulphatesCount, sulphateNI, aprioric);
		alcoholNI = checkIceberg(alcoholCount, alcoholNI, aprioric);
		qualityNI = checkIceberg(qualityCount, qualityNI, aprioric);
	}
	
	public HashMap<Integer,String> updateData(HashMap<String,String> map1,HashMap<Integer,String> map2){
		for(Map.Entry<String,String> entry:map1.entrySet()){
			String temp=entry.getKey();
			for(int i=0;i<map1.size();i++){
				if(map2.get(i)==temp){
					map2.put(i,"*");
				}
			}
		}
		return map2;
	}
	
	public void updatedData(){
		fixedAcidityIce=dimension.fixedAcidity;
		fixedAcidityIce=updateData(fAcidityNI,fixedAcidityIce);
		volatileAcidityIce = dimension.volatileAcidity;
		volatileAcidityIce = updateData(vAcidityNI, volatileAcidityIce);
		citricAcidIce = dimension.citricAcid;
		citricAcidIce = updateData(citricNI, citricAcidIce);
		residualSugarIce = dimension.residualSugar;
		residualSugarIce = updateData(rSugarNI,residualSugarIce);
		chloridesIce = dimension.chlorides;
		chloridesIce = updateData(chlorideNI, chloridesIce);
		freeSulfurIce = dimension.freeSulpharDioxide;
		freeSulfurIce = updateData(fSulfurNI,freeSulfurIce);
		totalSulfurIce = dimension.totalSulpharDioxide;
		totalSulfurIce = updateData(tSulfurNI, totalSulfurIce);
		densityIce = dimension.density;
		densityIce = updateData(densityNI, densityIce);
		pHIce = dimension.ph;
		pHIce = updateData(pHNI,pHIce);
		sulphatesIce = dimension.sulphates;
		sulphatesIce = updateData(sulphateNI,sulphatesIce);
		alcoholIce = dimension.alcohol;
		alcoholIce = updateData(alcoholNI,alcoholIce);
		qualityIce = dimension.quality;
		qualityIce = updateData(qualityNI, qualityIce);
	}
	
	public void createTable(){
		for(int i=0;i<fixedAcidityIce.size();i++){
			ArrayList<String> acidicValues=new ArrayList<String>();
			acidicValues.add(fixedAcidityIce.get(i));
			acidicValues.add(volatileAcidityIce.get(i));
			acidicValues.add(citricAcidIce.get(i));
			acidicValues.add(residualSugarIce.get(i));
			acidicValues.add(chloridesIce.get(i));
			acidicValues.add(freeSulfurIce.get(i));
			acidicValues.add(totalSulfurIce.get(i));
			acidicValues.add(densityIce.get(i));
			acidicValues.add(pHIce.get(i));
			acidicValues.add(sulphatesIce.get(i));
			acidicValues.add(alcoholIce.get(i));
			acidicValues.add(qualityIce.get(i));
			
			table.put(i,acidicValues);
		}
	}
	
	public void reducedTable(){
		int count=1;
		for(int i=0; i<table.size(); i++){
			if(!(reduceTable.containsKey(table.get(i)))){
				reduceTable.put(table.get(i), count);
			}
			else{
				int val = reduceTable.get(table.get(i));
				reduceTable.put(table.get(i), val+1);
			}
		}
		
	}
	
	public void totalCount(){
		for(Map.Entry<ArrayList<String>, Integer> i : reduceTable.entrySet()){
			int t = i.getValue();
			totalCount = totalCount + t;
		}
	}
	
	StarTree root=new StarTree(totalCount);
	
	public StarTree checkChild(StarTree root,String s){
		ArrayList<StarTree> child=root.children;
		StarTree temp;
		
		if(!child.isEmpty()){
			for(int i=0;i<child.size();i++){
				temp=child.get(i);
				if(temp.attribute.equals(s)){
					return temp;
				}
			}
		}
		return null;
	}
	
	public void createStarTree(ArrayList<String> row,int count){
		StarTree currNode=root;
		
		for(int i=0;i<row.size();i++){
			StarTree nodeStatus=checkChild(currNode,row.get(i));
			if(nodeStatus==null){
				StarTree newNode=new StarTree(row.get(i),count);
				if(i==row.size()-1){
					newNode.isLeaf=true;
				}
				currNode.children.add(newNode);
				if(currNode.children.size()>1){
					currNode.hasSibling=true;
				}
				currNode=newNode;
			}
			else{
				currNode=nodeStatus;
				currNode.count+=1;
			}
		}
	}
 	
	public void getRow(){
		for(Map.Entry<ArrayList<String>, Integer> i : reduceTable.entrySet()){
			createStarTree(i.getKey(),i.getValue());
		}
	}	
	
	public void starCubing(){
		dfs(root);
	}
	
//	public void backtracking(){
//		dfs(root);
//	}

	
	public void dfs(StarTree root){
		if(root.children.size()<=0){
			return  ;
		}
		
		for(int i=0;i<root.children.size();i++){
			 	 
		  dfs(root.children.get(i));
		  	
		}	
		return ;
	}
	

	
	public static void main(String[] args) {
		long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		long startTime = System.nanoTime();
		
 		WineQuality wq=new WineQuality();

 		Scanner sc=new Scanner(System.in);
 		
 		System.out.println("Enter aprioric condition");
 		int aprioric=sc.nextInt();
 		sc.close();
 		
 		wq.readFile();
 		wq.updateCount();
 		wq.updateNI(aprioric);
 		wq.updatedData();
 		wq.createTable();
 		wq.reducedTable();
 		wq.totalCount();
 		wq.getRow();
 		wq.starCubing();
		
 		System.out.println();
		//System.out.println();
		
		System.out.println("Compressed Base Table");
		
		for(Map.Entry<ArrayList<String>, Integer> i :wq.reduceTable.entrySet()){
			ArrayList<String> t = i.getKey();
			for(int j=0; j<t.size(); j++){
				System.out.print(t.get(j)+ " ");
			}
			System.out.println(":"+ i.getValue());
		}
		
//		System.out.println("Star Table");
//		for(Map.Entry<Integer, ArrayList<String>> i : wq.table.entrySet()){
//		System.out.print(i.getKey() + " : ");
//		ArrayList<String> t = i.getValue();
//		for(int j=0; j<t.size(); j++){
//		System.out.print(t.get(j)+ " ");
//		}
//		System.out.println();
//		}
		
		//Computation of Execution time and Memory usage
		long endTime   = System.nanoTime();
		long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		long actualMemUsed=afterUsedMem-beforeUsedMem;
		System.out.println("Memory used : "+actualMemUsed);
		long totalTime = endTime - startTime;
		System.out.println("Execution time : "+totalTime);

	}
}	
