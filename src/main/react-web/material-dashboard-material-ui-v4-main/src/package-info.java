/*
 * Marisa Ban: Upload.js in views/Upload folder
 * Marisa Ban non persistence extra: added boostrap elements to Upload.js 
 * (ie Container class, margin/spacing classes, and button variants)
 * Fritz Nastor - Modified User Profile.js: added different tabs for Profile editng, recipes, my drafts, following, and saved posts
 * Fritz Nastor - created an Upload.js component so I can show posts and drafts from the upload
 * Fritz Nastor - non persistence extra: added bootstrap elements to UserProfile.js
 * Marisa Ban and Fritz Nastor: Search.js in views/Search folder, also implemented following feature 
 * 
 * Matt Nice - setting which allowed switching between dark and light mode which was saved 
 * to the database.
 * Matt Nice extra: added mui components to Settings.js to make it look nice
 * 
 * 
 * Nick Dilallo:
 *  In views folder:
 *      Dashboard.js - Developed the dashboard to display all posts - Added features to the posts for saving and rating
 *      UserProfile.js - TabIndex = 4 aka the saved tab of profile page - Pull from Saved table and display any posts that the user clicked to save on the dashboard
 *  In Services folder:
 *      SavedService - Made functions/services for pulling and posting to Saved table via frontend
 *      UserService - Made the listUsers function for pulling and viewing all users on the site
 *      PostedRecipeService - Made the listUsers to pull into dashboard - Made the addRating for adding a Rating to a specific post
 *  In public/index.html:
 *      Changed the title that appears on user's tab
 *  In components/sidebar.js
 *      Cleaned up old/unused pages and removed incorrect titling
 */