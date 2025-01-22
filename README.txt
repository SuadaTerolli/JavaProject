Electronics Store Management Software

Electronics Store Management Software is a Java-based application created to help electronics stores manage their daily operations more efficiently. It has three levels of users: Administrator, Manager, and Cashier. Each of which has different permissions and features. It also allows different levels of access for users, such as Cashiers, Managers, and Administrators, ensuring that each role has access only to the functions they need. The software includes tools for managing inventory, processing customer bills, tracking sales, cashier performance, adding ,editing or deleting employees and managing supplier statistics. The manager can produce a pdf file format from his page.

The software is easy to use, with a simple interface and text files for storing all the store's important information. Users can easily add new products to the inventory, update existing details, and generate reports to analyze sales trends. Additionally, it allows users to export data, such as inventory and sales records, in formats like PDF or CSV.

Setting up the software is straightforward. It uses Maven for managing dependencies and works well with IntelliJ IDEA or other Java IDEs. In the future, the software could include features like online payment support and more advanced tools for analyzing sales and customer behavior. This makes it an excellent tool for electronics store owners and managers who want to make their operations smoother and more organized.

Information regarding implementation

The application is implemented using Java and JavaFX for GUI. Text files are used to store data for inventory, sales, and user information. Each text file contains structured data, with fields separated by delimiters (e.g., commas (CSV format)). Operations like adding, updating, and retrieving data involve reading from and writing to these text files. Operations such as adding, updating, and retrieving data are performed by reading from and writing to these files. For example, inventory data is managed by classes that handle adding, updating, and deleting items. These changes are reflected in the corresponding text files, ensuring data consistency. The billing module generates bills based on items purchased and updates the sales records file. These bills can also be saved as text or PDF files for record-keeping. Similarly, sales tracking involves storing transaction records in a text file and providing functions to query and generate reports. 

The software follows an MVC (Model-View-Controller) architecture, which helps separate the core business logic from the user interface. The Controller acts as an intermediary, managing the flow of data between the Model and the View, ensuring a smooth user experience. Core technologies include Java for the application's logic, Maven for dependency management, and file-based operations for data handling. 

App's screenshots

