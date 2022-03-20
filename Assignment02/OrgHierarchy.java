

public class OrgHierarchy implements OrgHierarchyInterface{

//root node
Node root;
// private HashMap<Integer, Node> employeMap = new HashMap<Integer, Node>();
MySearchTree searchTree = new MySearchTree();
int size = 0;

public boolean isEmpty(){
	return (size == 0);
} 

public int size(){
	return size;
}

// getting level of employee
// O(log(n))
public int level(int id) throws IllegalIDException{
	try {
		Node employeeNode = searchTree.get(id);
		return employeeNode.getLevel();
	} catch (Exception e) {
		throw new IllegalIDException("ID not found");
	}
} 

//hiring the owner
//O(1)
public void hireOwner(int id) throws NotEmptyException{
	if (size != 0) {
		throw new NotEmptyException("Tree not empty");
	} else {
		root = new Node(id);
		searchTree.insertNode(root);
		size++;
	}
}

//Hiring employee to boss
//O(log(n))
public void hireEmployee(int id, int bossid) throws IllegalIDException{
	try {
		Node boss = searchTree.get(bossid);
		Node employeeNode = new Node(id, boss);
		boss.addChild(employeeNode);
		searchTree.insertNode(employeeNode);
		size++;
	} catch (Exception e) {
		throw new IllegalIDException("bossid not found");
	}
	
} 

//Fire employee
//O(log(n))
public void fireEmployee(int id) throws IllegalIDException{
	try {
		Node employeeNode = searchTree.get(id);
		employeeNode.boss.removeChild(employeeNode);
		searchTree.deleteNode(id);
		size--;
	} catch (Exception e) {
		throw new IllegalIDException("id not found");
	}
}


//Firing employee and add childre to manageid
//O(log(n))
public void fireEmployee(int id, int manageid) throws IllegalIDException{
	try {
		Node employeeNode = searchTree.get(id);
		Node manageNode = searchTree.get(manageid);
		for (int i=0; i<employeeNode.childrenSize(); i++) {
			manageNode.addChild(employeeNode.children[i]);
			employeeNode.children[i].boss = manageNode;
		}
		employeeNode.boss.removeChild(employeeNode);
		searchTree.deleteNode(id);
		size--;
	} catch (Exception e) {
		throw new IllegalIDException("id or manageid not found");
	}
} 

//get boss of id employee
//O(log(n))
public int boss(int id) throws IllegalIDException{
	try {
		Node employeeNode = searchTree.get(id);
		return employeeNode.boss.getId();
	} catch (Exception e) {
		throw new IllegalIDException("id not found");
	}
}

//get lowest common boss
//O(log(n))
public int lowestCommonBoss(int id1, int id2) throws IllegalIDException{
	try {
		Node employee1 = searchTree.get(id1);
		Node employee2 = searchTree.get(id2);
		if (employee1 == root || employee1 == root) {
			return -1;
		} else {
			if (employee1.getLevel() > employee2.getLevel()) {
				while (employee1.getLevel() > employee2.getLevel()) {
					employee1 = employee1.boss;
				}
				if (employee1 == employee2) {
					return employee1.getId();
				}
			} else if (employee1.getLevel() < employee2.getLevel()) {
				while (employee1.getLevel() < employee2.getLevel()) {
					employee2 = employee2.boss;
				}
				if (employee1 == employee2) {
					return employee1.getId();
				}
			}
			while (employee1.boss.getId() != employee2.boss.getId()) {
				employee2 = employee2.boss;
				employee1 = employee1.boss;
			}
			return employee1.boss.getId();
		}
		
	} catch (Exception e) {
		throw new IllegalIDException("id not found");
	}
}


//toString
//O(nlog(n))
public String toString(int id) throws IllegalIDException{

	try {
		Node node = searchTree.get(id);
		String out = getAllEmployees(node);
		return out.substring(0, out.length()-1);
	} catch (Exception e) {
		throw new IllegalIDException("id not found");
	}
}

String getAllEmployees(Node node) {
	 String outString = "";
	EmployeeQueue q = new EmployeeQueue();
	q.enqueue(node);
	while (!q.isEmpty()) {
			int len = q.size();
		for (int i=0; i<len; i++) {
			Node current = q.pop();
			outString = outString+current.getId()+" ";
			if (current.childrenSize() != 0) {
				for (int j=0; j<current.childrenSize(); j++) {
					q.enqueue(current.children[j]);
				}
			}	
		}
		// outString = outString.substring(0, outString.length()-1)+",";
		q.quickSort();
	}
	return outString;
}

}
