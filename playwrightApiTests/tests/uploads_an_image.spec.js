// ********RoostGPT********
/*
Test generated by RoostGPT for test roost_test using AI Type Open AI and AI Model gpt-4

playwright test generated for endpoint: /pet/{petId}/uploadImage, with method: post
RoostTestHash=

*/

// ********RoostGPT********

import test, { expect, request } from "@playwright/test";
import fs from "fs";
import csvParser from "csv-parser";

function convertToNumber(value) {
  const intValue = parseInt(value);
  if (!isNaN(intValue) && value.trim() !== "") {
    return intValue;
  }

  const floatValue = parseFloat(value);
  if (!isNaN(floatValue) && value.trim() !== "") {
    return floatValue;
  }

  return value;
}

function readCsvData(filePath) {
  return new Promise((resolve, reject) => {
    const results = [];
    fs.createReadStream(filePath)
      .pipe(csvParser())
      .on("data", (data) => {
        const convertedData = {};
        Object.keys(data).forEach((key) => {
          convertedData[key] = convertToNumber(data[key]);
        });
        results.push(convertedData);
      })
      .on("end", () => resolve(results))
      .on("error", (err) => reject(err));
  });
}

test("uploads an image", async () => {
  const baseURL =  "https://petstore.swagger.io/v2";
  const context = await request.newContext({
    baseURL,
    extraHTTPHeaders: {
      "Content-Type": "application/json",
    },
  });

  const csvFilePath = `/var/tmp/Roost/RoostGPT/roost_test/1732093950/source/my-products/playwrightApiTests/tests/uploads_an_image.csv`
  const testData = await readCsvData(csvFilePath);

  for (let i = 0; i < testData.length; i++) {
    const csvDataRow = testData[i];
    const { statusCode, ...sampleData } = csvDataRow;

    const response = await context.post(`/pet/${sampleData.petId}/uploadImage`);
    expect(response.status()).toBe(statusCode);

    if (response.status() === statusCode) {
      console.log("Response for uploadFile is:", await response.json());
    }
  }
})