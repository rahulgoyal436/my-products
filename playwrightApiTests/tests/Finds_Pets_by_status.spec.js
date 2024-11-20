
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