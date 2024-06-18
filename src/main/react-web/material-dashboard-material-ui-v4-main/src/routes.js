/*!

=========================================================
* Material Dashboard React - v1.10.0
=========================================================

* Product Page: https://www.creative-tim.com/product/material-dashboard-react
* Copyright 2021 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/material-dashboard-react/blob/master/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
// @material-ui/icons
import Dashboard from "@material-ui/icons/Dashboard";
import Person from "@material-ui/icons/Person";
// core components/views for Admin layout
import DashboardPage from "views/Dashboard/Dashboard.js";
import Restaurant from "views/Restaurant/Restaurant";
import Upload from "views/Upload/Upload";
import UserProfile from "views/UserProfile/UserProfile.js";

// core components/views for Admin layout
import Search from "views/Search/Search";
import Settings from "views/Settings/Settings";
// core components/views for RTL layout

const dashboardRoutes = [
  {
    path: "/dashboard",
    name: "Dashboard",
    rtlName: "لوحة القيادة",
    icon: Dashboard,
    component: DashboardPage,
    layout: "/admin",
  },
  {
    path: "/user",
    name: "User Profile",
    rtlName: "ملف تعريفي للمستخدم",
    icon: Person,
    component: UserProfile,
    layout: "/admin",
  },
  {
    path: "/upload",
    name: "Upload",
    rtlName: "yeehaw",
    icon: Dashboard,
    component: Upload,
    layout: "/admin",
  },
  {

    path: "/restaurant",
    name: "Restaurant",
    rtlName: "woohoo", 
    icon: Dashboard,
    component: Restaurant,

  path: "/settings",
  name: "Settings",
  rtlName: "bruh",
  icon: Dashboard,
  component: Settings,
  layout: "/admin",
},
  {
    path: "/search",
    name: "Search",
    rtlName: "halp",
    icon: Dashboard,
    component: Search,
    layout: "/admin",
  },
];

export default dashboardRoutes;
