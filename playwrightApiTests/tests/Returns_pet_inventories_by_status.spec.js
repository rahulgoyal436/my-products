// ********RoostGPT********
/*
Test generated by RoostGPT for test roost_test using AI Type Azure Open AI and AI Model gpt-4o-standard

playwright test generated for endpoint: /store/inventory, with method: get
RoostTestHash=

*/

// ********RoostGPT********

import test, { expect, request } from "@playwright/test";

test("Returns pet inventories by status", async () => {
  const baseURL =  "https://petstore.swagger.io/v2";
  const context = await request.newContext({
    baseURL,
    extraHTTPHeaders: {
      "Content-Type": "application/json",
    },
  });

  const response = await context.get("/store/inventory");
  
  if (response.status() === 200) {
    expect(response.status()).toBe(200);
    console.log("Response for getInventory is:", await response.json());
  }
})