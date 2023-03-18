package main;

import java.io.*;
import java.util.*;


public class Solution {
    
    public static void main(String[] args) {
        
    	xor("10101","00101");
       
    }
    
    public static long maxProfit(List<Integer> a,
            int size){   
    	boolean b[] = new boolean[a.size()];
    	int max = a.get(a.size()-1);
    	b[b.length-1]=true;
    	for(int j=a.size()-2;j>-1;j--){
    		if( (b[j] = (a.get(j)>max)) ){
    			max = a.get(j);
    		}
    	}
    	long money = 0;
    	long stocks = 0;
    	for(int j=0;j<a.size();j++){
    		if(b[j]){
    			money+=a.get(j)*stocks;
                stocks=0;
    		}else{
    			stocks++;
                money-=a.get(j);
    		}
    	}
    	return money;
    }
    
    public static long flippingBits(long n) {
        char[] array1 = new char[32];
        char[] array = Long.toBinaryString(n).toCharArray();
        for(int i = 0; i <array1.length; i++)
            if(i<32-array.length)
                array1[i] = '1';
            else
                array1[i] = array[array.length-(32-i)] == '0' ? '1' : '0';
        return Long.parseLong(String.valueOf(array1), 2);
    }
    
    public static void xor(String s1, String s2) {
    	
    	long l1 = Long.parseLong(s1,2);
    	long l2 = Long.parseLong(s2,2);
    	long l3 = l1 ^l2;
    	char[] array1 = new char[s1.length()];
    	char[] array = Long.toBinaryString(l3).toCharArray();
        for(int i = 0; i <array1.length; i++)
            if(i<s1.length()-array.length)
                array1[i] = '0';
            else
                array1[i] = array[array.length-(s1.length()-i)] ;
    	
    	System.out.println(String.valueOf(array1));
    }

    public static List<List<Integer>> textQueries(List<String> sentences, List<String> queries) {
        
        List<List<Integer>> list_list = new ArrayList<List<Integer>>();
        
        for(String query : queries){
            System.out.println(query);
            
        }
        
        for(String sentence : sentences){
            System.out.println(sentence);
        }
        
        return null;
        
    }
	
	public static List<Integer> getAdjacentNodes(List<List<Integer>> edges, int node){
		
		List<Integer> list = new ArrayList<Integer>();
		
		for(List<Integer> v : edges) {
			if(node == v.get(0))
				list.add(v.get(1));
			if(node == v.get(1))
				list.add(v.get(0));
		}
		
		return list;
	}
	
	public static List<Integer> bfs(int n, int m, List<List<Integer>> edges, int s) {
	    
	    Queue<Integer> queue = new PriorityQueue<Integer>();
	    List<Integer> dists = new ArrayList<Integer>(n);
	    
	    int[] distances = new int[n];
	    
	    for(int i = 0; i<n;i++)
	    	dists.set(i, 0);
	    
	    queue.add(s);
	    
	    while(!queue.isEmpty()) {
	    	int node = queue.poll();
	    	
	    	List<Integer> l = getAdjacentNodes(edges, node);
	    	
	    	for(Integer x : l) {
	    		queue.add(x);
	    		distances[x] = distances[x]+1;
	    	}
	    }
	    
	    for(int i = 0; i<n;i++)
	    	if(dists.get(i) == 0)
	    		dists.set(i, -1);
	    
		return dists;
	}
	
	public static void write() {
		
		Scanner in = new Scanner(System.in);
		String s = "";
		Stack<String> prev_s = new Stack<String>();
		int counter = in.nextInt();
		in.nextLine();
		int i = 0;
		while(i<counter) {
			String read = in.nextLine();
			String inst = read.split(" ")[0];
			if(inst.contentEquals("1")) {
				prev_s.add(s);
				s += read.split(" ")[1];
				
			} else if(inst.contentEquals("2")) {
				prev_s.add(s);
				s = s.substring(0,s.length()- Integer.valueOf(read.split(" ")[1]));
				
			} else if(inst.contentEquals("3")) {
				System.out.println(s.charAt(Integer.valueOf(read.split(" ")[1])-1));
			} else if(inst.contentEquals("4")) {
				s = prev_s.pop();
			}
			i++;
		}
		in.close();
		
		return;
	}
	
	public static void preOrder(Node root) {
		Stack<Node> stack = new Stack<Node>();
		
		stack.push(root);
		
		while(!stack.empty()) {
			Node node = stack.pop();
			
			System.out.println(node.getValue());
			if(node.right != null)
				stack.push(node.right);
			if(node.left != null)
				stack.push(node.left);
		}
		
		return;
	}
	
	 public static int legoBlocks(int n, int m) {
	        // Write your code here
	        if (n < 2 || m < 1) return 0;
	        if (m == 1) return 1;
	        
	        // - Step 1: consider only one row
	        long [] total = new long [m + 1];
	        
	        // set a flag (-1) to calculate only once
	        for (int i = 0; i < total.length; i++)
	            total[i] = -1;
	        
	        fillTot(total, m);
	        
	        // - Step 2: extend to all rows
	        for (int i = 0; i < total.length; i++) {
	            long tmp = 1;
	            for (int j = 0; j < n; j++) {
	                tmp = (tmp * total[i]) % MOD;
	            }
	            total[i] = tmp;
	        }
	        
	        // - Step 3: subtract the vertically unstable
	        // don't calculate the vertically unstable at first
	        long [] result = new long [m + 1];
	        // set a flag (-1) to calculate only once
	        for (int i = 0; i < result.length; i++)
	            result[i] = -1;
	        
	        getResult(total, result, m);
	        return (int) (result[m] % MOD);
	    }
	    
	public static long getResult(long [] total, long [] result, int i) {
	        if (result[i] == -1) {
	            if (i == 1) {
	                // solution 1
	                // result[i] = 0;
	                
	                // solution 2
	                result[i] = 1;
	            }
	            else {
	                // solution 1
	                // result[i] = 0;
	                // for (int j = 1; j < i; j++) {
	                //     result[i] += ((total[j] - getResult(total, result, j)) * total[i - j]) % MOD;
	                // }
	                
	                // solution 2            
	                result[i] = total[i];
	                for (int j = 1; j < i; j++) {
	                    result[i] -= (getResult(total, result, j) * total[i - j]) % MOD;
	                }
	            }
	        }
	        
        return result[i];
    }
	 
	// fill totals partially
    public static long fillTot(long [] total, int i) {
        if (i < 0) return 0;
        
        if (total[i] == -1) {
            if (i == 0 || i == 1) 
                total[i] = 1;
            else 
                total[i] = (fillTot(total, i - 1) + fillTot(total, i - 2) + fillTot(total, i - 3) + fillTot(total, i - 4)) % MOD;
        }
        
        return total[i];
    }
	
	public static boolean allAbove(int k, List<Integer> A) {
		for(Integer i : A)
			if(i<k)
				return false;
		return true;
	}
	
	public static int cookies(int k, List<Integer> A) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(A);
        int iterations = 0;
        
        while (pq.size() > 0) {
            Integer lowest = pq.poll();
            System.out.println(lowest);
            if (lowest >= k) {
                return iterations;
            }
            Integer secondLowest = pq.poll();
            if (secondLowest != null) {
                final Integer combined = lowest + (2 * secondLowest);
                pq.offer(combined);
            } else {
                return -1; //this will only happen if 1 item left less than k
            }
            
            iterations++;
        }
        
        return iterations; //would only get here is pq size of 0
	}
	
	public static int pairs(int k, List<Integer> arr) {
	    // Write your code here
		HashSet<Integer> set = new HashSet<>();
	    for(int i=0;i<arr.size();i++) set.add(arr.get(i));
	    int ans = 0;
	    for(int i:set)
	        ans+=(set.contains(i+k))?1:0;
	    return ans;
	}
	
	public static boolean search(String s, int idx, Map<Character, Character> map, char toSearch) {
		if(s.length() % 2 != 0)
			return false;
		if(idx >= s.length())
			return false;
		
		System.out.println(s.charAt(idx));
		System.out.println(toSearch);
		System.out.println("----------");
		if(!map.containsKey(s.charAt(idx)) && toSearch != s.charAt(idx))
			return false;
		if(!map.containsKey(s.charAt(idx)) && toSearch == s.charAt(idx))
			return true;
		return search(s, idx+1, map, map.get(s.charAt(idx)));
		
	}
	
	public static boolean isBalancedAlt(String s) {
		Map<Character, Character> map = new HashMap<Character,Character>();
		map.put('{','}');
		map.put('(',')');
		map.put('[',']');
		
		return search(s, 0, map, map.get(s.charAt(0)));
	}
	
	public static String isBalanced(String s) {
	    // Write your code here
		Map<Character, Character> map = new HashMap<Character,Character>();
		map.put('{','}');
		map.put('(',')');
		map.put('[',']');
	    for(int i = 0; i<s.length()/2; i++) {
	        if(map.get(s.charAt(i)) != s.charAt(s.length()-i-1))
	                return "NO";
		}
	    return "YES";
	}
	
	public static boolean next(String str, int i, char c, char neededChar) {
		
		if( c == '{' && str.charAt(i) == neededChar)
			return true;
		else if(c == '{')
			return next(str, i+1, c, '}');
		
		if( c == '[' && str.charAt(i) == neededChar)
			return true;
		else if(c == '[')
			return next(str, i+1, c, ']');
		
		if( c == '(' && str.charAt(i) == neededChar)
			return true;
		else if(c == '(')
			return next(str, i+1, c, ')');
		
		return false;
	
	}
	
	public static boolean bracket(String str) {
		
		char c = str.charAt(0);
		if(c == '{')
			return next(str, 1, c, '}');
		if(c == '[')
			return next(str, 1, c, ']');
		if(c == '(')
			return next(str, 1, c, ')');
		//{ [ ]	
		//{ [  ] }
		return false;
	}
	
	public static String bracketSolver(String str) {
		String left = "";
		Map<String, String> map = new HashMap<String,String>();
		map.put("{","}");
		map.put("(",")");
		map.put("[","]");
		String right = "";
    	for(int i = 0; i<str.length(); i++) {
    	  left += str.charAt(i);
    	  right += map.get(String.valueOf(str.charAt(i)));
    	}
    	
        Pattern p = Pattern.compile("^"+left+"..."+right);
        Matcher m = p.matcher(str);
        if(!m.find())
      	  return "FALSE";
    	
    	return "TRUE";
	}
	
	public static void queueFunction() {
		Scanner in = new Scanner(System.in);
		Queue<Integer> queue = new LinkedList<Integer>();
		String op = "";
		int n = Integer.valueOf(in.nextLine());
		int i = 0;
		while(i<n) {
			op = in.nextLine();
			if(op.split(" ")[0].contentEquals("2")) {
				queue.remove();
			}else if(op.split(" ")[0].contentEquals("3")) {
				System.out.println(queue.peek());
			}else if(op.split(" ")[0].contentEquals("1")) {
				queue.add(Integer.valueOf(op.split(" ")[1]));
			}
			i++;
		}
		in.close();
	}
	
	public static SinglyLinkedListNode merge(SinglyLinkedListNode head1, SinglyLinkedListNode head2){
		
		SinglyLinkedListNode head = head1;
		SinglyLinkedListNode curr = head1;
		SinglyLinkedListNode prev = null;
		while(curr!=null) {
			prev = curr;
			curr = curr.next;
		}
		prev.next = head2;
		curr = head2;
		
		//sort
		SinglyLinkedListNode current = head, index = null;
		 
        int temp;
 
        if (head == null) {
            return null;
        }
        else {
            while (current != null) {
                // Node index will point to node next to
                // current
                index = current.next;
 
                while (index != null) {
                    // If current node's data is greater
                    // than index's node data, swap the data
                    // between them
                    if (current.data > index.data) {
                        temp = current.data;
                        current.data = index.data;
                        index.data = temp;
                    }
 
                    index = index.next;
                }
                current = current.next;
            }
        }
		
		return head;
	}
	
	public int breadthFirstSearch(int nRows, Node origin, Node destination, int nCols, int[][] map) {
		
		int[][] visited = new int[nRows][nCols];
		
		Queue<Node> q = new PriorityQueue<Node>();
		
		for(int i = 0; i < visited.length; i++)
			for(int j = 0; j < visited[0].length; j++) {
				visited[i][j] = 0;
			}
		
		q.add(new Node(0,0));
		
		while(!q.isEmpty()) {
			
			Node n = q.poll();
			if(visited[n.getI()][n.getJ()] == 1)
				continue;
			
			visited[n.getI()][n.getJ()] = 1;
			
			if(n.getI() == 0 || n.getJ() == 0 || n.getI() == nRows || n.getJ() == nCols)
				continue;
			
			
			
		}
		
		return 0;
		
	}

    public static List<String>
    sortLexicographically(List<String> strArr, int size)
    {
		//improve this
        for (int i = 0; i < strArr.size(); i++) {
            for (int j = i + 1; j < strArr.size(); j++) {
                if (strArr.get(i).compareToIgnoreCase(strArr.get(i))
                    > 0) {
                    String temp = strArr.get(i);
                    strArr.add(i, strArr.get(j));
                    strArr.add(j,temp);
                }
            }
        }
        
        List<String> s= new ArrayList<String>();
        for(int i = size-1;i>=0; i--) {
        	s.add(strArr.get(i));
        }
        
        return s;
    }

    public static List<List<String>> getProductSuggestions(List<String> products, String search) {
    	
    	try {
    	String s = "";
    	ArrayList<List<String>> list_list = new ArrayList<List<String>>();
    	List<String> aux = new ArrayList<String>();
    	for(int i = 0; i<search.length(); i++) {
    		aux = new ArrayList<String>();
    		s += search.charAt(i);
            for (String pattern : products){
              Pattern p = Pattern.compile("^"+s);
              Matcher m = p.matcher(pattern);
              if(m.find()) {
            	  aux.add(pattern);
              }
            }
            
            list_list.add((ArrayList<String>) sortLexicographically(aux, Math.min(3, aux.size())));

    	}
    	return (List<List<String>>)list_list;
    	
       } catch (Exception ex) {
    	   System.out.println("Error!!!");
    	   ex.printStackTrace();
       }
    	
    	return null;
    }

}