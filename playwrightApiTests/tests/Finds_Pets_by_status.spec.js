// ********RoostGPT********
/*
Test generated by RoostGPT for test roost_test using AI Type Azure Open AI and AI Model gpt-4o-standard

playwright test generated for endpoint: /pet/findByStatus, with method: get
RoostTestHash={
  "paramDesc": {
    "status": "array"
  },
  "headerDesc": {},
  "reqBodyDesc": {},
  "resBodyDesc": {
    "200": "[\n  {\n    \"id\": \"integer\",\n    \"category\": {\n      \"id\": \"integer\",\n      \"name\": \"string\"\n    },\n    \"name\": \"string\",\n    \"photoUrls\": [\n      \"string\"\n    ],\n    \"tags\": [\n      {\n        \"id\": \"integer\",\n        \"name\": \"string\"\n      }\n    ],\n    \"status\": \"enum: available, pending, sold\"\n  }\n]",
    "400": "Invalid status value with empty response body"
  }
}

*/

// ********RoostGPT********

import test, { expect, request } from "@playwright/test";
test("Finds Pets by status", async () => {
  const baseURL =  "https://petstore.swagger.io/v2";
  const context = await request.newContext({
    baseURL,
    extraHTTPHeaders: {
      "Content-Type": "application/json",
    },
  });

  const response = await context.get("/pet/findByStatus");
  
  if (response.status() === 200) {
    expect(response.status()).toBe(200);
    console.log("Response for findPetsByStatus is:", await response.json());
  }
  
  if (response.status() === 400) {
    expect(response.status()).toBe(400);
    console.log("Response for findPetsByStatus is:", await response.json());
  }
})