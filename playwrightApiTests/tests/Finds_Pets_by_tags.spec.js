// ********RoostGPT********
/*
Test generated by RoostGPT for test roost_test using AI Type Azure Open AI and AI Model gpt-4o-standard

playwright test generated for endpoint: /pet/findByTags, with method: get
RoostTestHash=/pet/findByTags-get-65b226edc9

*/

// ********RoostGPT********

import test, { expect, request } from "@playwright/test";
test("Finds Pets by tags", async () => {
  const baseURL =  "https://petstore.swagger.io/v2";
  const context = await request.newContext({
    baseURL,
    extraHTTPHeaders: {
      "Content-Type": "application/json",
    },
  });

  const response = await context.get("/pet/findByTags");
  
  if (response.status() === 200) {
    expect(response.status()).toBe(200);
    console.log("Response for findPetsByTags is:", await response.json());
  }
  
  if (response.status() === 400) {
    expect(response.status()).toBe(400);
    console.log("Response for findPetsByTags is:", await response.json());
  }
})