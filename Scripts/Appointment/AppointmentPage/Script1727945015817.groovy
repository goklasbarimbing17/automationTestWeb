import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.awt.Robot
import java.awt.event.KeyEvent

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_CURA Healthcare Service/MakeAppointment/select_Tokyo CURA Healthcare Center        _5b4107'), 
    facility, true)

WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/MakeAppointment/label_Apply for hospital readmission'))

WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/MakeAppointment/label_Medicaid'))

// Get today's date in the 'DD-MM-YYYY' format
LocalDate today = LocalDate.now()
String formattedDate = today.format(DateTimeFormatter.ofPattern('dd-MM-yyyy'))

// Define the Test Object for the date input field
TestObject visitDateField = findTestObject('Object Repository/Page_CURA Healthcare Service/MakeAppointment/input_Visit Date (Required)_visit_date')

// Set the date in the input field
if(typeTest.equals('valid')) {
	WebUI.setText(visitDateField, formattedDate)
}

WebUI.setText(findTestObject('Object Repository/Page_CURA Healthcare Service/MakeAppointment/textarea_Comment_comment'), 
    comment)

WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/MakeAppointment/button_Book Appointment'))

if(typeTest.equals('invalid')) {
	WebUI.verifyElementAttributeValue(visitDateField, 'value', '', 10)
	// Create a Robot instance
	Robot robot = new Robot()
	
	// Press the Esc key
	robot.keyPress(KeyEvent.VK_ESCAPE)
	robot.keyRelease(KeyEvent.VK_ESCAPE)
	
} else {
	WebUI.verifyTextPresent('Appointment Confirmation', true)
}

