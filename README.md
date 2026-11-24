 <h1>🛒 Minute Cart – AI-Powered Quick Commerce Using MCP</h1>

<b>Shop smarter. Just ask AI.</b>

Minute Cart is an AI-powered quick-commerce application that brings grocery shopping directly into AI conversations using the Model Context Protocol (MCP).

Inspired by quick-commerce platforms like Blinkit, Zepto, and Instamart, Minute Cart provides a simulated grocery shopping experience where users can discover products, manage their shopping carts, place orders, and track deliveries using natural-language conversations with AI clients such as Claude and other MCP-compatible applications.

Instead of navigating through multiple screens, users can simply tell their AI assistant what they need, and the assistant interacts with Minute Cart through MCP tools to perform the required operations.

<h1>✨ Key Features</h1>

<h2>🛍️ Product Discovery</h2>

∎ Browse all available products.<br> ∎ Search products using partial names or keywords.<br> ∎ Find products by category.<br> ∎ Explore products by brand.<br> ∎ Retrieve specific product details.<br> ∎ Identify products using their unique product IDs.<br>

<h2>🛒 Smart Cart Management</h2>

∎ Add products to the cart using natural-language requests.<br> ∎ Specify product quantities while adding products.<br> ∎ View current cart contents.<br> ∎ Clear the shopping cart.<br>

<h2>📦 Order Management</h2>

∎ Place orders using products available in the cart.<br> ∎ Retrieve all previously placed orders.<br> ∎ View the four most recent orders.<br> ∎ Check the status of pending orders.<br>

<h2>🚚 Automated Order & Delivery Status Management</h2>

Minute Cart uses scheduled tasks in the Spring Boot backend to simulate a real-world quick-commerce delivery lifecycle.

Once an order is successfully placed, its delivery status is automatically updated at scheduled intervals, simulating the complete journey of a quick-commerce delivery service.

<b>Order Lifecycle:</b>

 
<img width="160" height="249" alt="image" src="https://github.com/user-attachments/assets/2159e6be-1ecf-48bf-9942-11d407901cad" />


<b>Automated Status Transitions:</b>

∎ <b>PROCESSING_ORDER:</b> Order has been successfully placed and is being processed.<br> ∎ <b>PACKING:</b> Products are being packed for delivery.<br> ∎ <b>OUT_FOR_DELIVERY:</b> Order has been dispatched and is on its way to the customer.<br> ∎ <b>DELIVERED:</b> Order has been successfully delivered.<br>

<b>How It Works:</b>

∎ User places an order through the AI assistant.<br> ∎ The MCP server invokes the create_order tool.<br> ∎ The core backend creates the order with an initial status of PROCESSING_ORDER.<br> ∎ A scheduled task automatically updates the order status through the subsequent delivery stages.<br> ∎ The order eventually reaches DELIVERED after approximately 10 minutes.<br> ∎ Users can ask their AI assistant to retrieve the latest order status using the get_Order_Status MCP tool.<br>

<h2>👤 User Management</h2>

∎ Retrieve user details through AI conversations.<br>
<img width="784" height="493" alt="image" src="https://github.com/user-attachments/assets/c08ebcd5-158e-43d7-9d63-680793a529a7" />

<h2>🤖 AI-Powered Shopping Experience</h2>

Minute Cart allows users to interact with their shopping backend using natural language.

<b>Example Requests:</b>

∎ "Show me all available products."<br> ∎ "Find Amul cheese slices."<br> ∎ "Add 2 packets of Amul cheese slices to my cart."<br> ∎ "Show my cart."<br> ∎ "Place my order."<br> ∎ "Show my recent orders."<br> ∎ "What is the status of my order?"<br>

<h2>🍳 Intelligent Recipe-Based Shopping</h2>

One of the interesting use cases of Minute Cart is conversational shopping based on recipes and requirements.

Users can ask the AI assistant to identify ingredients and add them to their cart.

<b>Example:</b>

"I want to prepare egg sandwiches for 5 people. Add all the required ingredients to my cart."

The AI assistant can:

∎ Understand the user's requirement.<br> ∎ Identify the ingredients required for the recipe.<br> ∎ Estimate quantities based on the number of people.<br> ∎ Search for matching products in Minute Cart.<br> ∎ Retrieve the corresponding product IDs.<br> ∎ Add the selected products to the cart with appropriate quantities.<br> ∎ Display the updated cart for review.<br>

<b>More Example Requests:</b>

∎ "Add snacks and beverages for a party of 10 people."<br> ∎ "I want to prepare pasta for 4 people. Add the ingredients."<br> ∎ "Prepare a grocery list for a weekend get-together."<br>
<br>
<img width="665" height="956" alt="Screenshot 2026-11-23 211747" src="https://github.com/user-attachments/assets/fc528145-6179-45d6-9aab-527eb2360d61" />
<img width="825" height="982" alt="Screenshot 2026-11-24 173338" src="https://github.com/user-attachments/assets/7a523f9e-0527-4fa2-9d6c-7d06a5289f38" />
<img width="790" height="965" alt="Screenshot 2026-11-24 174321" src="https://github.com/user-attachments/assets/cff7a5aa-686c-4c48-8853-fba129e697fa" />
<br>
## 🏗️ Architecture

Minute Cart consists of two independent Spring Boot applications:

<b>1. Minute Cart Core Backend</b>

Handles the application's business logic and exposes the required backend functionality through APIs.

<b>2. Minute Cart MCP Server</b>

Exposes the core backend capabilities as MCP tools, allowing AI clients to interact with the application.
  
 
## 🔧 MCP Tools

<h2>Minute Cart currently exposes the following MCP tools:</h2>

<h2>🛍️ Product Discovery Tools</h2>

 
<i>get_products</i>	➡  Retrieves available products.  <br>
<i>get_products_by_productName</i>	➡  Searches products using partial names, keywords or similar terms.<br>
<i>get_product_by_productName</i> ➡	  Retrieves a product using its exact name.<br>
<i>get_products_by_category</i>	➡  Retrieves products belonging to a particular category.<br>
<i>get_products_by_brand</i>	➡  Retrieves products belonging to a particular brand.<br>
<i>get_productId</i>	➡  Retrieves the product ID using its name.<br>

<h2>🛒 Cart Management Tools</h2>

 
<i>add_To_Cart</i> ➡ 	Adds a product to the cart using its product ID and quantity.<br>
<i>view_cart</i>	➡  Retrieves the current cart details.<br>
<i>clear_cart</i>	➡  Clears the shopping cart.<br>

<h2>📦 Order Management Tools</h2>

 
<i>create_order</i>➡	Creates an order using the products in the cart.<br>
<i>get_orders</i>➡	Retrieves all previously placed orders.<br>
<i>get_recent_orders</i>➡	Retrieves the four most recent orders.<br>
<i>get_Order_Status</i>	➡Retrieves the status of pending orders.<br>

<h2>👤 User Management Tools</h2>

 
<i>get_user_details</i>	➡Retrieves user information.<br>

<h2>⚙️ How AI Uses These Tools</h2>

For example, when a user says:

<b>"Add Amul Cheese Slices Plain to my cart."</b>

The AI assistant can:

∎ Call <i>get_products_by_productName</i> to search for the product.<br> ∎ Identify the correct product from the search results.<br> ∎ Retrieve its product ID if necessary using get_productId.<br> ∎ Invoke add_To_Cart with the product ID and requested quantity.<br> ∎ Confirm the result to the user.<br>

This allows the AI to perform multi-step shopping operations using the exposed MCP tools.
 
