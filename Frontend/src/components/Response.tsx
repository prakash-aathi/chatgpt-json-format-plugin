import React, { useState } from 'react';

interface props {
    response: object;
}

const Response = ({ response }: props) => {
    const [isCopied, setIsCopied] = useState(false);
    const [isStringCopied, setIsStringCopied] = useState(false);

    let formattedJson = JSON.stringify(response, null, 2).replace(/\\n|\\/g, '');
    formattedJson = formattedJson.slice(1, -1);

    const handleCopyClick = () => {
        navigator.clipboard.writeText(formattedJson);
        setIsCopied(true);

        // Reset the "Copied" state after a brief delay
        setTimeout(() => {
            setIsCopied(false);
        }, 1500);
    };

    const handleCopyJsonString = () => {
        navigator.clipboard.writeText(JSON.stringify(response, null, 2));
        setIsStringCopied(true);

        // Reset the "Copied" state after a brief delay
        setTimeout(() => {
            setIsStringCopied(false);
        }, 1500);
    };

    return (
        <div className=''>
            {JSON.stringify(response) !== '{}' && (
                <div className='md:w-[60%] mx-2 flex md:mx-auto mb-8 pb-10 mt-4'>
                    <div className='mr-4 font-semibold text-xl mt-8'>AI</div>
                    <div className='bg-gray-200 font-medium py-4 px-4 drop-shadow-xl my-4 rounded-xl whitespace-pre-line w-full'>
                        {isStringCopied && <p className='text-green-600 text-right '>JSON String copied to clipboard!</p>}
                        {isCopied && <p className='text-green-600 text-right'>JSON copied to clipboard!</p>}
                        <div className='p-4 border rounded-lg bg-gray-900 text-white'>
                            <div className='md:flex justify-between items-center mb-2'>
                                <h2 className='text-lg text-center font-semibold'>JSON Data</h2>

                                <div>
                                    <button
                                        className='px-2 py-1 mx-1 bg-blue-600 hover:bg-blue-700 rounded text-white'
                                        onClick={handleCopyJsonString}
                                    >
                                        Copy JSON String
                                    </button>
                                    <button
                                        className='px-2 py-1 mx-1 bg-blue-600 hover:bg-blue-700 rounded text-white'
                                        onClick={handleCopyClick}
                                    >
                                        Copy JSON
                                    </button>
                                </div>
                            </div>
                            <pre className='overflow-x-auto whitespace-pre-line'>
                                <code>{formattedJson}</code>
                            </pre>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default Response;
