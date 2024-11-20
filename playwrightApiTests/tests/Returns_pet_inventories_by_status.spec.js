
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