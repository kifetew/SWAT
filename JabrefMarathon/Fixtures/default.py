from net.sf.jabref import JabRefMain
from marathon.playback import *

class Fixture:
    '''Default Fixture'''
    def start_application(self):
        '''Starts the application. The arguments can be changed by modifying the args array'''
        args = []
        JabRefMain.main(args)

    def teardown(self):
        '''Marathon executes this method at the end of test script.'''
        pass

    def setup(self):
        '''Marathon executes this method before the test script. The application needs to be
        started here. You can add other tasks before start_application.'''
        self.start_application()

    def test_setup(self):
        '''Marathon executes this method after the first window of the application is displayed.
        You can add any Marathon script elements here.'''
        pass

fixture = Fixture()
