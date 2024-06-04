import React from "react";

import ListingTest from "components/Test/ListingTest.jsx";
import CommentsListing from 'components/Test/CommentTest.jsx';
import Settings from 'components/Test/Settings.jsx';

export default function Test() {
  return (
    <div>
        <p>Hello</p>
        <ListingTest />

        <h1>Comments Application</h1>
        <CommentsListing />


        <h1>settings</h1>
        <Settings />
    </div>
  );
}
